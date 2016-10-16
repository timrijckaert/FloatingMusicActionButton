package be.rijckaert.tim.animatedvector

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import be.rijckaert.tim.vector.sample.library.R

class FloatingMusicActionButton : FloatingActionButton {

    private var isShowingPlayIcon: Boolean = true
    private var currentMode: Mode

    private val playToPauseDrawable: Drawable by lazy { ContextCompat.getDrawable(context, R.drawable.play_to_pause_animation) }
    private val pauseToPlayDrawable: Drawable by lazy { ContextCompat.getDrawable(context, R.drawable.pause_to_play_animation) }
    private val playToStopDrawable: Drawable by lazy { ContextCompat.getDrawable(context, R.drawable.play_to_stop_animation) }
    private val stopToPlayDrawable: Drawable by lazy { ContextCompat.getDrawable(context, R.drawable.stop_to_play_animation) }

    private val currentDrawable: Drawable
        get() {
            return getAnimationDrawable()
        }

    fun getMode(mode: Int): Mode = if (mode == 0) Mode.PLAY_TO_PAUSE else Mode.PLAY_TO_STOP

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val typedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.FloatingMusicActionButton,
                0, 0)

        try {
            currentMode = getMode(typedArray.getInteger(R.styleable.FloatingMusicActionButton_mode, 0))
        } finally {
            typedArray.recycle()
        }

        this.setOnClickListener { playAnimation() }
        init()
    }

    private fun init() {
        isShowingPlayIcon = true
        this.setImageDrawable(currentDrawable)
    }

    private fun getAnimationDrawable(): Drawable =
            if (isShowingPlayIcon && currentMode == Mode.PLAY_TO_PAUSE) {
                playToPauseDrawable
            } else if (!isShowingPlayIcon && currentMode == Mode.PLAY_TO_PAUSE) {
                pauseToPlayDrawable
            } else if (isShowingPlayIcon && currentMode == Mode.PLAY_TO_STOP) {
                playToStopDrawable
            } else {
                stopToPlayDrawable
            }

    private fun playAnimation() {
        this.setImageDrawable(currentDrawable)
        (currentDrawable as Animatable).start()
        isShowingPlayIcon = !isShowingPlayIcon
    }

    open fun setMode(mode: Mode, shouldInvalidateOnChange : Boolean = false) {
        currentMode = mode
        if (shouldInvalidateOnChange)
            init()
    }

    enum class Mode(private val modeInt: Int) {
        PLAY_TO_PAUSE(0),
        PLAY_TO_STOP(1)
    }
}