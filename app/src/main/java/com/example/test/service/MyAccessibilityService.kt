package com.example.test.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.example.test.showToast
import java.util.logging.LogManager
import java.util.logging.Logger

class MyAccessibilityService : AccessibilityService() {
    var instances:MyAccessibilityService?=null
    private val TAG=MyAccessibilityService::class.java.simpleName
    override fun onInterrupt() {
        Log.i(TAG,"onInterrupt")
    }

    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.i(TAG,"onAccessibilityEvent")
        when (p0?.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
                Log.i(TAG,"TYPE_VIEW_CLICKED")
            }
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                showToast("${p0.packageName} \n ${p0.className}")
                Log.i(TAG,"${p0.packageName} \n ${p0.className}")
            }
            null -> {

            }
        }

    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG,"onUnbind")
        instances=null

        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG,"onStartCommand")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG,"onServiceConnected")
        instances=this
    }
}