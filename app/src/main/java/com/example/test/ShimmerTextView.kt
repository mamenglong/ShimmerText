package com.example.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import com.example.shimmerlibrary.Shimmer
import com.example.shimmerlibrary.Shimmer1

class ShimmerTextView: TextView {
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,defStyleAttr)

    val shimmer = Shimmer()
    val shimmer1=Shimmer1()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        shimmer.mPaint= Paint()
        shimmer1.setPaint(paint)
        shimmer1.setContent("nihao")
        //shimmer.textSize=60f
//        shimmer.mViewWidth=measuredWidth
//        shimmer.textSize=textSize
//        shimmer.mWidthDivideBy=5
//        shimmer.init()
        shimmer1.init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        shimmer.run()
        shimmer1.run()
        shimmer1.setContent("terdsechd")
        postInvalidateDelayed(100)
    }

}