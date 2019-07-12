package com.example.shimmerlibrary

import android.os.Looper
import android.util.Log

object Logger{
    fun i(tag:String,value:String){
        if (BuildConfig.DEBUG)
            Log.i(tag,value)
    }
    fun d(tag:String,value:String){
            Log.d(tag,value)
    }
}