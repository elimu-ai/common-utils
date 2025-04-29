# common-utils

[![](https://jitpack.io/v/ai.elimu/common-utils.svg)](https://jitpack.io/#ai.elimu/common-utils)

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
   implementation 'ai.elimu:common-utils:1.0.1'
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

### How to publish a snapshot for local development & testing?

1. Under `publishing` -> `repositories` block in the same Gradle script: Replace the existing `maven` repo by `mavenLocal()`
2. Run `./gradlew clean utils:publishReleasePublicationToMavenLocal` from project's root folder
3. In app side: Add `mavenLocal()` to the `repositories` block in `build.gradle` script
4. You're now ready to test the `-SNAPSHOT` version!

## How to release a new version?

1. Update versionCode, versionName in `utils/build.gradle` file by increment either major/minor/patch number and merge to `main` (Do not remove the `SNAPSHOT` suffix)
2. Trigger [`Release`](https://github.com/elimu-ai/common-utils/actions/workflows/gradle-release.yml) task in Github Actions on `main` branch
3. Draft a new Release in https://github.com/elimu-ai/common-utils/releases

> [!IMPORTANT]
> After you publish a new release, remember to also bump the version in all Android app repos that depend on the `utils` library:
> * https://github.com/elimu-ai/launcher/blob/main/gradle/libs.versions.toml
> * https://github.com/elimu-ai/kukariri/blob/main/gradle/libs.versions.toml
> * https://github.com/elimu-ai/vitabu/blob/main/gradle/libs.versions.toml
> * https://github.com/elimu-ai/filamu/blob/main/gradle/libs.versions.toml

---

<p align="center">
  <img src="https://github.com/elimu-ai/webapp/blob/main/src/main/webapp/static/img/logo-text-256x78.png" />
</p>
<p align="center">
  elimu.ai - Free open-source learning software for out-of-school children 🚀✨
</p>
<p align="center">
  <a href="https://elimu.ai">Website 🌐</a>
  &nbsp;•&nbsp;
  <a href="https://github.com/elimu-ai/wiki#readme">Wiki 📃</a>
  &nbsp;•&nbsp;
  <a href="https://github.com/orgs/elimu-ai/projects?query=is%3Aopen">Projects 👩🏽‍💻</a>
  &nbsp;•&nbsp;
  <a href="https://github.com/elimu-ai/wiki/milestones">Milestones 🎯</a>
  &nbsp;•&nbsp;
  <a href="https://github.com/elimu-ai/wiki#open-source-community">Community 👋🏽</a>
  &nbsp;•&nbsp;
  <a href="https://www.drips.network/app/drip-lists/41305178594442616889778610143373288091511468151140966646158126636698">Support 💜</a>
</p>
