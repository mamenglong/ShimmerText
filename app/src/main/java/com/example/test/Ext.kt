package com.example.test

import android.os.Handler
import android.os.Looper
import android.widget.Toast


fun showToast(msg:String)= run {
    val looper=Looper.getMainLooper()
    if (looper== Looper.myLooper()){
            Toast.makeText(TestApplication.instances,msg,Toast.LENGTH_SHORT).show()
    }
}
class Ext {

}