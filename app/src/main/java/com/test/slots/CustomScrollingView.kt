package com.test.slots

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import com.test.slots.data.ITEMS

class CustomScrollingView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var currentImage: ImageView
    private var nextImage: ImageView
    private var oldValue = 0
    private var lastResult = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling, this)
        currentImage = findViewById(R.id.current_image)
        nextImage = findViewById(R.id.next_image)

        nextImage.translationY = height.toFloat()
    }

    fun setValueRandom(image: Int, rotateCount: Int) {
        currentImage.animate().translationY(-height.toFloat()).setDuration(ANIMATION_DURATION)
            .start()
        nextImage.translationY = nextImage.height.toFloat()
        setImage(nextImage, image)
        nextImage.animate().translationY(0f).setDuration(ANIMATION_DURATION)
            .setListener(object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    setImage(currentImage, oldValue % 6)
                    currentImage.translationY = 0f
                    if (oldValue != rotateCount) {
                        setValueRandom(image, rotateCount)
                        oldValue++
                    } else {
                        lastResult = 0
                        oldValue = 0
                    }
                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }

            }).start()
    }

    fun setImage(imageView: ImageView, value: Int) {
        ITEMS.values().find { it.value == value }?.image?.let { imageView.setImageResource(it) }
        imageView.tag = value
        lastResult = value
    }



    companion object {
        private const val ANIMATION_DURATION = 500L
    }
}