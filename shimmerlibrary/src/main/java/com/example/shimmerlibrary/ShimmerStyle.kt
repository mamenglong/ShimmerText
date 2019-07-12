package com.xjlmh.classic.view

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Shader

interface ShimmerStyle{
    fun setAnimating(boolean: Boolean=true)
    fun setLinearGradient(linearGradient: LinearGradient?=null)
    fun setGradientMatrix(matrix: Matrix?=null)
    fun setDefaultTranslate(default: Int=0)
    fun setLinearGradientColors(colors:IntArray?)
    fun setShaderTileMode(tileMode: Shader.TileMode?)
    fun setLinearGradientPositions(position:FloatArray?)
    fun initShimmerDefault(){
        println("initShimmerDefault")
        setAnimating()
        setLinearGradient()
        setGradientMatrix()
        setDefaultTranslate()
    }
}