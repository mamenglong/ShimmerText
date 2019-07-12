package com.example.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.xjlmh.classic.view.Shimmer
import java.util.jar.Attributes

class ShimmerTextView: TextView {
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,defStyleAttr)

    val shimmer = Shimmer()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        shimmer.mPaint=paint
        //shimmer.textSize=60f
        shimmer.mViewWidth=measuredWidth
        shimmer.textSize=textSize
        shimmer.mWidthDivideBy=5
        shimmer.init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        shimmer.run()
        postInvalidateDelayed(100)
    }

}