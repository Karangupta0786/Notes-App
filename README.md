# Project Setup


      //Add Plugins
      id("kotlin-kapt")
      
       // Room
       implementation("androidx.room:room-runtime:2.6.1")
       
       // To use Kotlin annotation processing tool (kapt)
       kapt("androidx.room:room-compiler:2.6.1")
       implementation("androidx.room:room-ktx:2.6.1")

       // ViewModel
       implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

       // LiveData
       implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

       // Coroutines
       implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
       implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
