package be.rijckaert.tim.vector.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import be.rijckaert.tim.animatedvector.FloatingMusicActionButton

class MainActivity : AppCompatActivity() {

    private var fab: FloatingMusicActionButton? = null
    private var fabCurrentMode: TextView?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.playToPauseFab) as FloatingMusicActionButton
        fabCurrentMode = findViewById(R.id.fab_current_mode) as TextView
        //fab?.changeMode(FloatingMusicActionButton.Mode.STOP_TO_PLAY)

        fab?.setOnMusicFabClickListener(object : FloatingMusicActionButton.OnMusicFabClickListener {
            override fun onClick(view: View) {
                updateText()
            }
        })
    }

    private fun updateText() {
        val str = "Current Mode: ${fab?.currentMode?.name} \nOpposite Mode: ${fab?.getOppositeMode()}"
        fabCurrentMode?.text = str
    }

    fun playToPause(view : View) {
        fab?.changeMode(FloatingMusicActionButton.Mode.PLAY_TO_PAUSE)
        updateText()
    }

    fun pauseToPlay(view : View) {
        fab?.changeMode(FloatingMusicActionButton.Mode.PAUSE_TO_PLAY)
        updateText()
    }

    fun playToStop(view : View) {
        fab?.changeMode(FloatingMusicActionButton.Mode.PLAY_TO_STOP)
        updateText()
    }

    fun stopToPlay(view : View) {
        fab?.changeMode(FloatingMusicActionButton.Mode.STOP_TO_PLAY)
        updateText( )
    }
}