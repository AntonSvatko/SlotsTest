package com.test.slots.ui.fragments.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("ClickableViewAccessibility")
class NoClickableRecyclerView(context: Context, attrs: AttributeSet?): RecyclerView(context, attrs) {
    override fun onTouchEvent(e: MotionEvent?) = false
    override fun onInterceptTouchEvent(e: MotionEvent?) = false
}