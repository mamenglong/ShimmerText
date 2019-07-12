package com.example.shimmerlibrary

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView

class M_TextView2 : TextView {

    private var mLinearGradient: LinearGradient? = null
    private var mGradientMatrix: Matrix? = null
    private var mPaint: Paint? = null
    private var mViewWidth = 0
    private var mTranslate = 0

    constructor(context: Context) : super(context) {
        // TODO Auto-generated constructor stub
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        // TODO Auto-generated constructor stub
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // TODO Auto-generated constructor stub
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mViewWidth == 0) {
            mViewWidth = measuredWidth
            if (mViewWidth > 0) {
                mPaint = paint
                mLinearGradient = LinearGradient(
                    0f,
                    0f,
                    mViewWidth.toFloat(),
                    0f,
                    intArrayOf(Color.BLUE, -0x1, Color.BLUE), null,
                    Shader.TileMode.CLAMP
                )
                mPaint!!.shader = mLinearGradient
                mGradientMatrix = Matrix()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth
            }
            mGradientMatrix!!.setTranslate(mTranslate.toFloat(), 0f)
            mLinearGradient!!.setLocalMatrix(mGradientMatrix)
            postInvalidateDelayed(50)
        }
    }
}