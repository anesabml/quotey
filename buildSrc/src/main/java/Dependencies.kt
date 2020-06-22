
object Releases {
    val versionCode = 27
    val versionName = "2.7"
}

object Versions {
    val kotlin = "1.3.61"
    val gradle = "3.6.1"
    val compileSdk = 29
    val minSdk = 21
    val targetSdk = 29
    val appCompat = "1.1.0"
    val recyclerview = "1.1.0"
    val preference = "1.1.0"
    val coreKtx = "1.2.0"
    val constraintlayout = "1.1.3"
    val fragmentTesting = "1.3.0-alpha01"
    val material = "1.2.0-alpha02"
    val palette = "1.0.0"
    val koin = "2.0.1"
    val coroutines = "1.3.3"
    val lifecycle = "2.2.0"
    val room = "2.2.4"
    val okHttp = "4.3.1"
    val retrofit = "2.7.1"
    val retrofitCoroutines = "0.9.2"
    val retrofitMoshi = "2.7.1"
    val glide = "4.11.0"
    val workManager = "2.3.3"
    val junit = "4.13"
    val androidxTest = "1.1.0"
    val archCoreTest ="2.1.0"
    val androidTestRunner = "1.2.0"
    val espressoCore = "3.2.0"
    val robolectric = "4.3.1"
    val mockito = "3.2.4"
}

object Libraries {
    // KOTLIN
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // ANDROID
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val preference = "androidx.preference:preference:${Versions.preference}"
    val palette = "androidx.palette:palette-ktx:${Versions.palette}"

    val material = "com.google.android.material:material:${Versions.material}"

    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

    // KOIN
    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    // ROOM
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // OKHTTP
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // RETROFIT
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshi}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    // GLIDE
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // WORK MANAGER
    val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
}

object TestLibraries {
    // ANDROID TEST
    val junit = "junit:junit:${Versions.junit}"

    // Core library
    val androidTestCore = "androidx.test:core:${Versions.androidxTest}"
    val androidTestCoreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"

    // AndroidJUnitRunner and JUnit Rules
    val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    val androidTestRules = "androidx.test:rules:${Versions.androidTestRunner}"

    // Assertions
    val junitKtx = "androidx.test.ext:junit-ktx:${Versions.androidxTest}"
    val truth = "androidx.test.ext:truth:${Versions.androidxTest}"

    // Test helpers for LiveData
    val archCore = "androidx.arch.core:core-testing:${Versions.archCoreTest}"

    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val fragment = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"

    // Coroutines
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    // Room DB
    val room = "androidx.room:room-testing:${Versions.room}"

    // Robolectric
    val robolectric =  "org.robolectric:robolectric:${Versions.robolectric}"

    // Mockito
    val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"

    // WORK MANAGER
    val workManager = "androidx.work:work-testing:${Versions.workManager}"
}