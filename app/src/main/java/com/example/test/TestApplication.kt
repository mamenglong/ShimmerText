package com.example.test

import android.app.Application
import org.litepal.LitePal

class TestApplication: Application() {
    companion object{
        var instances:Application?=null
    }
    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        if (instances==null)
            instances=this
    }
}