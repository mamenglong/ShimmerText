package com.example.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DevilReceiver: BroadcastReceiver() {
      val Devil="BroadcastReceiver"
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i("DevilReceiver","onReceive")
        
    }
}