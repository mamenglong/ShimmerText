package com.example.test.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log

@SuppressLint("Registered")
class TimerWidgetService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //发送广播通知 widget 更新 状态
        Log.i("TimerWidgetService","onStartCommand")
        sendBroadcast( Intent("com.example.test.app_widget_update_time").addFlags(0x01000000).putExtra("time", System.currentTimeMillis()))
        return Service.START_STICKY
    }
}