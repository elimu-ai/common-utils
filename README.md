# common-utils
This repository provides commonly shared functions being used across Android apps

## How to use TTS function?

The TTS function is provided via [TextToSpeechViewModel](https://github.com/elimu-ai/common-utils/blob/1.0.1/utils/src/main/java/ai/elimu/common/utils/viewmodel/TextToSpeechViewModelImpl.kt),
which is implemented using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) and
[kapt](https://kotlinlang.org/docs/kapt.html)
So, make sure you have Hilt & kapt imported in your projects via below check-list:

1. Add Hilt and kapt plugins in `app/build.gradle` file
   ```groovy
   apply plugin: 'dagger.hilt.android.plugin'
   apply plugin: 'org.jetbrains.kotlin.android'
   apply plugin: 'kotlin-kapt'
   ```
2. Add Hilt & `common-utils` dependencies in `app/build.gradle` file
   ```groovy
   implementation 'com.github.elimu-ai:common-utils:1.0.1'
   implementation 'com.google.dagger:hilt-android:2.55'
   kapt 'com.google.dagger:hilt-compiler:2.55'
   ```
3. Add Hilt & Kotlin gradle plugin classpaths to project's `build.gradle` file
   ```groovy
   classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0"
   classpath "com.google.dagger:hilt-android-gradle-plugin:2.55"
   ```
4. Add `@HiltAndroidApp` to your Application class
5. Add `@AndroidEntryPoint` to your Activity/Fragment
6. Initialize your `TextToSpeechViewModel` in your `onCreate` method of your Activity/Fragment
   ```kotlin
   private lateinit var ttsViewModel: TextToSpeechViewModel
   fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       ttsViewModel = ViewModelProvider(this)[TextToSpeechViewModelImpl::class.java]
   }
   ```
7. Now you're ready to use the Text to Speech function
