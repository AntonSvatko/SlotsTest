package com.test.slots.ui.adapter.layoutManager

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class SpeedyLinearLayoutManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean,
    private val callBack: (() -> Unit)? = null
) :
    LinearLayoutManager(context, orientation, reverseLayout) {
    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        val linearSmoothScroller: LinearSmoothScroller =
            object : LinearSmoothScroller(recyclerView.context) {
                override fun calculateSpeedPerPixel(displayMetricsTrell: DisplayMetrics): Float {
                    return MILLISECONDS_PER_INCH / displayMetricsTrell.densityDpi
                }

                override fun onStop() {
                    super.onStop()
                    callBack?.invoke()
                }
            }
        linearSmoothScroller.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }


    companion object {
        var MILLISECONDS_PER_INCH = 140f
    }
}