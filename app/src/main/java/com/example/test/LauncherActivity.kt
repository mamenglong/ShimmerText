package com.example.test

import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_launcher.*
import android.widget.Toast
import com.example.test.service.FloatWindowService


class LauncherActivity : AppCompatActivity() {
    private val TAG=LauncherActivity::class.java.simpleName

    private val map= mutableMapOf<String,Class<*>>()
    private val fcMap= mutableMapOf<String,String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        map["重启"]=LauncherActivity::class.java
        map["DataBingTest"]=DataBingActivity::class.java
        map["mvp测试"]=MVPTestActivity::class.java
        map["通知测试"]=NotificationActivity::class.java
        map["文字时钟"]=StageActivity::class.java
        map.forEach{ entry ->
            val btn=Button(this)
            btn.text=entry.key
            btn.setOnClickListener {

                startActivity(Intent(this,entry.value))
            }
            mainView.addView(btn)
        }
        fcMap["Accessibility"]= "无障碍服务"
        fcMap.forEach{entry->
            val btn=Button(this)
            btn.text=entry.key
            btn.setOnClickListener {
                //
                Log.i(TAG,"Accessibility")
                if (!isServiceEnabled()){
                    val accessibleIntent =  Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                    startActivity(accessibleIntent)
                }
                startFloatingService()
            }
            mainView.addView(btn)
        }

    }
    //检查服务是否开启
    private fun isServiceEnabled():Boolean {
        var accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

        val accessibilityServices = accessibilityManager.getEnabledAccessibilityServiceList(
            AccessibilityServiceInfo.FEEDBACK_ALL_MASK
        )
        for (info in accessibilityServices) {
            if (info.id.contains("com.example.test/.service.MyAccessibilityService")) {
                return true
            }
        }
        return false
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun startFloatingService() {
        if (FloatWindowService.isStarted) {
            return
        }
        if (!Settings.canDrawOverlays(this)) {
            showToast("当前无权限，请授权")
            startActivityForResult(
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")),
                0
            )
        } else {
            startService(Intent(this, FloatWindowService::class.java))
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show()
                startService(Intent(this, FloatWindowService::class.java))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
