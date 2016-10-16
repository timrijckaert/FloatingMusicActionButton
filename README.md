# FloatingMusicActionButton 
[![](https://jitpack.io/v/timrijckaert/FloatingMusicActionButton.svg)](https://jitpack.io/#timrijckaert/FloatingMusicActionButton)

A simple FloatingActionButton with vector animations between different media states.
This a part of a blog post on Medium.
https://medium.com/@timrijckaert/play-pause-stop-animated-vector-drawable-88a9df956d20#.edub54uks

##Installation
Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add the dependency

```gradle
dependencies {
        compile 'com.github.timrijckaert:FloatingMusicActionButton:v1.0'
}
```

##Usage
You can check out the sample provided in the repository.

```xml
<be.rijckaert.tim.animatedvector.FloatingMusicActionButton
        android:layout_width="wrap_content"
        app:backgroundTint="@color/colorAccent"
        android:layout_gravity="center"
        app:mode="playToPause"
        android:layout_height="wrap_content" />
```

You can change the behavior of the fab dynamically using.

```kotlin
val musicFab = fab as FloatingMusicActionButton
musicFab.setMode(FloatingMusicActionButton.Mode.PLAY_TO_STOP)
```