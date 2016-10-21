package be.rijckaert.tim.vector.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import be.rijckaert.tim.animatedvector.FloatingMusicActionButton

class MainActivity : AppCompatActivity() {

    private var fab: FloatingMusicActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.playToPauseFab) as FloatingMusicActionButton
        fab?.setMode(FloatingMusicActionButton.Mode.PLAY_TO_PAUSE, true)

        fab?.setOnMusicFabClickListener(object : FloatingMusicActionButton.OnMusicFabClickListener {
            override fun onClick(view: View) {
                Toast.makeText(this@MainActivity, "Fab clicked!!! IsPlayIcon: ${fab?.isShowingPlayIcon}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun animateFab(view : View) {
        fab?.playAnimation()
    }
}