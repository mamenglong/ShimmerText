package com.xjlmh.classic.view

import android.graphics.*
import com.example.shimmerlibrary.Logger
class Shimmer() : ShimmerStyle {



    private var mLinearGradient: LinearGradient? = null
    private var mGradientMatrix: Matrix? = null
    var mPaint = Paint()
    var mViewWidth = 100
    private var isAnimating = true
    private var mTranslate = 0
    var mWidthDivideBy = 5
    private val DEFAULT_COLORS = intArrayOf(0xff00ffff.toInt(), 0xffffffff.toInt(), 0xff00ffff.toInt())
    private val DEFAULT_POSITIONS = floatArrayOf(0.0f, 0.5f, 1.0f)
    private val DEFAULT_SHADER_TILEMODE=Shader.TileMode.CLAMP
    private var colors = DEFAULT_COLORS//intArrayOf(0xff00ffff.toInt(), 0xffffffff.toInt(), 0xff00ffff.toInt())
    private var positions = DEFAULT_POSITIONS
    private var content: String = ""
    private var tileMode:Shader.TileMode= DEFAULT_SHADER_TILEMODE
    var textSize=30f
    /**
     * 构建基础数据
     */
    constructor(content: String) : this() {
        this.content = content
//        if (content.length>7)
//            setLinearGradientColors(intArrayOf(0xFF4DDA11.toInt(), 0xffffffff.toInt(), 0xFF4DDA11.toInt()))
        Logger.i("Shimmer", "shimmer create")
        println("shimmer create")
        val rect = Rect()
        mPaint.getTextBounds(content, 0, 1, rect)
        mViewWidth = mPaint.measureText(content).toInt()
    }

    /**
     * 初始化，所有配置设置好以后调用，调用以后设置关联，  LinearGradient首次实例化之后不可修改，因此采用每次修改都需实例化
     */
    fun init(){
        mPaint.textSize = textSize
        initShimmerDefault().apply {
            Logger.i("Shimmer", "initShimmerDefault")
        }
        mPaint.shader = mLinearGradient
    }
    override fun setShaderTileMode(tileMode: Shader.TileMode?) {
        tileMode?.let {
            this.tileMode=it
            return
        }
        this.tileMode= DEFAULT_SHADER_TILEMODE
    }
    override fun setLinearGradientColors(colors: IntArray?) {
        Logger.i("Shimmer", "setLinearGradientColors before:${this.colors.toList()}  ")
        colors?.let {
            this.colors=it
            Logger.i("Shimmer", "setLinearGradientColors  after: ${this.colors.toList()}")
            return
        }
        this.colors=DEFAULT_COLORS
    }

    override fun setLinearGradientPositions(position: FloatArray?) {
        Logger.i("Shimmer", "setLinearGradientPositions before:${this.positions.toList()}  ")
        position?.let {
            this.positions=it
            Logger.i("Shimmer", "setLinearGradientPositions  after: ${this.positions.toList()}")
            return
        }
        this.positions=DEFAULT_POSITIONS
    }

    override fun setAnimating(b: Boolean) {
        Logger.i("Shimmer", "setAnimating:$b")
        isAnimating = b
    }


    override fun setLinearGradient(linearGradient: LinearGradient?) {
        Logger.i("Shimmer", "setLinearGradient:$linearGradient")
        mLinearGradient = linearGradient ?: LinearGradient((-mViewWidth).toFloat(), 0f, 0f, 0f,
                colors,
                positions,tileMode)
    }

    override fun setGradientMatrix(matrix: Matrix?) {
        Logger.i("Shimmer", "setGradientMatrix:$matrix")
        mGradientMatrix = matrix ?: Matrix()
    }

    override fun setDefaultTranslate(i: Int) {
        Logger.i("Shimmer", "setDefaultTranslate:$i")
        mTranslate = i
    }

    /**
     * 绘制函数,调用画出阴影
     */
    fun run() {
        Logger.i("Shimmer", "run")
        if (isAnimating && mGradientMatrix != null) {
            mTranslate += mViewWidth / mWidthDivideBy
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = 0
            }
            Logger.i("Shimmer", "mTranslate :$mTranslate")
            mGradientMatrix!!.setTranslate(mTranslate.toFloat(), 0f)
            this.mLinearGradient?.setLocalMatrix(mGradientMatrix)
        }
        log()
    }

    /**
     * 关闭流光效果
     */
    fun stop() {
        isAnimating = false
    }
    fun log(){
        Logger.i("Shimmer","content:$content mViewWidth:$mViewWidth isAnimating:$isAnimating :mTranslate:$mTranslate  tileMode：$tileMode mWidthDivideBy:$mWidthDivideBy colors:${colors.toList()} positions:${positions.toList()}")
    }
    fun getContent()=content
}