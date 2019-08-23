package com.example.test.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class DevilService : Service() {
    
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("DevilService","onStartCommand")
        val intent = Intent(this, TimerWidgetService::class.java)
        startService(intent.addFlags(0x01000000))
        return  START_STICKY//super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i("DevilService","onDestroy")
        val intent = Intent(this, TimerWidgetService::class.java)
        startService(intent.addFlags(0x01000000))
        super.onDestroy()
    }
}
