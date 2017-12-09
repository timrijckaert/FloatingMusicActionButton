package be.rijckaert.tim.animatedvector

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import be.topradio.app.R
import be.topradio.app.ui.widget.FloatingMusicActionButton.Mode.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.delay

class FloatingMusicActionButton : FloatingActionButton {

    private val maximumAnimationDuration by lazy { context.resources.getInteger(R.integer.play_button_animation_duration).toLong() }

    var onMusicFabClickListener: OnMusicFabClickListener? = null
    private var currentMode: Mode = PLAYPAUSE
        set(value) {
            field = value
            setImageDrawable(field)
        }

    //<editor-fold desc="Chaining Constructors">
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.FloatingMusicActionButton,
                0, 0)
        try {
            currentMode = getMode(typedArray.getInteger(R.styleable.FloatingMusicActionButton_mode, PLAYPAUSE.styleableInt))
        } finally {
            typedArray.recycle()
        }

        this.setOnClickListener { eventActor.offer(it) }
    }
    //</editor-fold>

    //<editor-fold desc="Helpers">
    private fun setImageDrawable(mode: Mode) {
        val animatedVector = ContextCompat.getDrawable(context, mode.drawableRes)
        this.setImageDrawable(animatedVector)
    }

    private fun getMode(styleableInt: Int): Mode = listOf(PLAYPAUSE, PAUSEPLAY, PLAYSTOP, STOPPLAY).first { it.styleableInt == styleableInt }

    private val eventActor = actor<View>(UI) {
        channel.consumeEach {
            this@FloatingMusicActionButton.drawable.startAsAnimatable()
            onMusicFabClickListener?.onClick(it)
            delay(maximumAnimationDuration)
            val oppositeMode = currentMode.getOppositeMode()
            currentMode = oppositeMode
        }
    }

    private fun Drawable.startAsAnimatable() = (this as Animatable).start()

    sealed class Mode(val styleableInt: Int, @DrawableRes val drawableRes: Int) {
        object PLAYPAUSE : Mode(0, R.drawable.play_to_pause_animation)
        object PAUSEPLAY : Mode(1, R.drawable.pause_to_play_animation)
        object PLAYSTOP : Mode(2, R.drawable.play_to_stop_animation)
        object STOPPLAY : Mode(3, R.drawable.stop_to_play_animation)
    }

    private val opposites = mapOf(
            PLAYPAUSE to PAUSEPLAY,
            PAUSEPLAY to PLAYPAUSE,
            PLAYSTOP to STOPPLAY,
            STOPPLAY to PLAYSTOP
    )
    private fun Mode.getOppositeMode() = opposites[this]!!

    interface OnMusicFabClickListener {
        fun onClick(view: View)
    }
    //</editor-fold>
}
