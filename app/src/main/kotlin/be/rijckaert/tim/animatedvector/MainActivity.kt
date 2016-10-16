package be.rijckaert.tim.animatedvector

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startAnimation(view: View) {
        val imageView = view as ImageView
        val drawable = imageView.drawable
        if (drawable is Animatable) {
            drawable.start()
        }
    }
}