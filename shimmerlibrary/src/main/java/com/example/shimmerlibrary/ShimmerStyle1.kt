package com.xjlmh.classic.view

import android.graphics.*

interface ShimmerStyle1{
    fun setAnimating(boolean: Boolean=true)
    fun setLinearGradient(linearGradient: LinearGradient?=null)
    fun setGradientMatrix(matrix: Matrix?=null)
    fun setDefaultTranslate(default: Int=0)
    fun setLinearGradientColors(colors:IntArray?)
    fun setShaderTileMode(tileMode: Shader.TileMode?=Shader.TileMode.CLAMP)
    fun setLinearGradientPositions(position:FloatArray?)
    fun setContent(content:String)
    fun setViewWidth(width:Int=0)
    fun setWidthDivideBy(num:Int=5)
    fun setPaint(paint: Paint?)
    fun setTextSize(size:Float?)
    fun initShimmerDefault(){
        println("initShimmerDefault")
        setAnimating()
        setContent("我是默认文字")
        setLinearGradient()
        setGradientMatrix()
        setDefaultTranslate()
    }
    fun initShimmer(){
        println("initShimmer")
        setLinearGradient()
        setGradientMatrix()
    }
}