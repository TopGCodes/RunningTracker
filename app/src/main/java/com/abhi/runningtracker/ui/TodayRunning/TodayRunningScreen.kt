package com.abhi.runningtracker.ui.TodayRunning

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import checkSelfPermission
import com.abhi.runningtracker.R
import com.abhi.runningtracker.service.RunningTrackerService
import requestPermission
import java.security.AccessController.checkPermission
import java.security.Permission


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TodayRunningScreen(
    modifier: Modifier,
    viewModel: TodayRunningViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var isGrantedPermission by remember {
        mutableStateOf(true)
    }
    if(!isGrantedPermission){
        requestPermission(Manifest.permission.ACTIVITY_RECOGNITION)
    }
    Column(modifier.fillMaxSize()) {
        StepsCountContent(
            onCreateNewSessions = {
                // check the permissions and launch the service
                Toast.makeText(context, "started", Toast.LENGTH_SHORT).show()
                val isGranted = checkSelfPermission(context, Manifest.permission.ACTIVITY_RECOGNITION)
                if(isGranted){
                    //Launch the service
                    val intent = Intent(context, RunningTrackerService::class.java).also {
                        it.action = RunningTrackerService.Actions.Start.toString()
                    }
                    context.startService(intent)
                }
                else{
                    isGrantedPermission = false
                    Toast.makeText(context, "not granted", Toast.LENGTH_SHORT).show()
                }
            },
            modifier
        )
    }

}

@Composable
fun StepsCountContent(onCreateNewSessions: () -> Unit, modifier: Modifier) {
     StepsCountCard(modifier = modifier,
         onCreateNewSession = onCreateNewSessions
         )
}


@Preview
@Composable
fun StepsCountCard(
    modifier: Modifier = Modifier,
    stepcounts: String = "10",
    onCreateNewSession: () -> Unit = {}
) {
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(all = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Steps Counter",
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )

            Button(onClick = { onCreateNewSession.invoke() }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                    contentDescription = "add"
                )
            }
        }

        Row(
            modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Steps Count :",
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )
            Text(
                text = stepcounts,
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun PreviousSessions(
    modifier: Modifier = Modifier,
    list: List<String> = listOf()
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(all = 4.dp)) {
          Text(text = "Previous Sessions",
              modifier = modifier.padding(all = 8.dp),
              fontSize = 20.sp
              )
        LazyColumn {
            items(list){

            }
        }
    }
}
