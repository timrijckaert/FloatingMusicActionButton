# FloatingMusicActionButton 
[![](https://jitpack.io/v/timrijckaert/FloatingMusicActionButton.svg)](https://jitpack.io/#timrijckaert/FloatingMusicActionButton)

A simple FloatingActionButton with vector animations between different media states.
This a part of a blog post on Medium.
https://medium.com/@timrijckaert/play-pause-stop-animated-vector-drawable-88a9df956d20#.edub54uks

<img src="sample.gif" width=300></img>

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
        compile 'com.github.timrijckaert:FloatingMusicActionButton:v2.0.4'
}
```

##Usage
You can check out the sample provided in the repository.

```xml
<be.rijckaert.tim.animatedvector.FloatingMusicActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        app:backgroundTint="@color/colorAccent"
        android:layout_gravity="center"
        app:mode="playToPause"
        android:layout_height="wrap_content" />
```

###Change behaviour
You can change the behaviour of the fab dynamically.
Options are transitions between play and stop or play and pause.

```kotlin
val musicFab = fab as FloatingMusicActionButton
musicFab.changeMode(FloatingMusicActionButton.Mode.PLAY_TO_STOP)
```

###Click Listener
In order to receive click events register a 'OnMusicFabClickListener'

```kotlin
fab.setOnMusicFabClickListener(object : FloatingMusicActionButton.OnMusicFabClickListener {
    override fun onClick(view: View) {
        //do stuff
    }

})
```

###Force Animation

```kotlin
val musicFab = fab as FloatingMusicActionButton
musicFab.playAnimation()
```
