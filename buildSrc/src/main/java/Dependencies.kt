object Versions {
    const val nav_version = "2.8.0"
    const val hiltversion = "2.51.1"
    const val retrofitversion = "2.11.0"
    const val gsonVersion = "2.9.0"
    const val okhttpVersion = "4.12.0"
    const val gson = "2.11.0"
    const val jsonSerialization = "1.7.3"
    const val pagingVersion = "3.3.2"
    const val glide = "4.16.0"
    const val lottie = "6.4.0"
    const val corountine = "1.3.9"
    const val lifecyccleversion = "2.4.0"
    const val work_version = "2.9.1"
    const val room_version = "2.6.1"
    const val material_version = "1.3.0"
}

object jetpack{
    const val material3 ="androidx.compose.material3:material3:${Versions.material_version}"
}

object hilt {
    const val hiltandroid = "com.google.dagger:hilt-android:${Versions.hiltversion}"
    const val kaptcompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltversion}"
    const val navigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltversion}"
}

object JetpackNavigation {
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.nav_version}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui:${Versions.nav_version}"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitversion}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.gsonVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttplogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
}

object Gson {
    const val convertor = "com.google.code.gson:gson:${Versions.gson}"
}

object Coroutines {
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.corountine}"
}

object Serialization {
    const val json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.jsonSerialization}"
}

object Paging {
    const val runtime = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    const val compose = "androidx.paging:paging-compose:${Versions.pagingVersion}"
}

object ImageLibrary {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Animation {
    const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"
}

object Lifecycle {
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecyccleversion}"
    const val lifecyclescope =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecyccleversion}"
}

object Workmanager {
    const val runtime = "androidx.work:work-runtime-ktx:${Versions.work_version}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val ktx = "androidx.room:room-ktx:${Versions.room_version}"
}