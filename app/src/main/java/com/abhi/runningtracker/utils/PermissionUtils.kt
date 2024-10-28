import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


data class RationaleState(
    val title: String? = null,
    val detial: String? = null,
    val isGotoSettings: Boolean = false,
)


@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun requestPermission(permission: String = Manifest.permission.POST_NOTIFICATIONS) {

    val context = LocalContext.current

    val permissionState = rememberPermissionState(permission = permission)

    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()

    var denialCount by remember {
        mutableStateOf(0)
    }

    var showBottomSheet by remember {
        mutableStateOf(permissionState.status.isGranted.not())
    }

    var showGotoSettings by remember {
        mutableStateOf(false)
    }

    val bottomSheetContentState by remember(permissionState.status, showGotoSettings) {
        mutableStateOf(getPermissionSheetContentState(permissionState, showGotoSettings))
    }

    if (denialCount >= 2 && permissionState.status.shouldShowRationale.not()) {
        showGotoSettings = true
    }

    if (permissionState.status.isGranted) {
        closeBottomSheet(scope, sheetState)
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            shape = BottomSheetDefaults.ExpandedShape
        ) {
            with(bottomSheetContentState) {
                Column(
                    Modifier.padding(all = 5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title.toString(),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                    )
                    Text(
                        text = detial.toString(),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                    Row(
                        Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            if (showGotoSettings.not()) {
                                denialCount++
                                permissionState.launchPermissionRequest()
                            } else {
                                openAppSettings(context)
                            }
                        }) {
                            if (showGotoSettings.not()) Text(text = "Allow")
                            else Text(text = "Go to Settings")
                        }
                        Button(onClick = {
                            showBottomSheet = false
                            closeBottomSheet(scope, sheetState)
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}

fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}

@OptIn(ExperimentalMaterial3Api::class)
fun closeBottomSheet(scope: CoroutineScope, sheet: SheetState) {
    scope.launch {
        sheet.hide()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun getPermissionSheetContentState(
    permissionState: PermissionState,
    showGotoSettings: Boolean
): RationaleState {
    /*
       * This case will execute when user deny the permission in first attempt, so we show rationale
       * and if user deny the permission even from the ratioanle , we need to let user to go to settings
     */
    return if (permissionState.status.shouldShowRationale || showGotoSettings) {
        RationaleState(
            title = "Without this permission",
            detial = "Please grant the permission to enjoy the app feature",
            isGotoSettings = showGotoSettings
        )
    } else if (showGotoSettings) {
        RationaleState(
            title = "Without this permission",
            detial = "Please grant the permission in the settings",
            isGotoSettings = showGotoSettings
        )
    } else {
        // This case will execute for initial permission request(1 st time)
        RationaleState(
            title = "Allow this permission",
            detial = "Please grant the permission to enjoy the app feature",
            isGotoSettings = showGotoSettings
        )
    }
}



fun checkSelfPermission(context : Context , permission : String): Boolean {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}



