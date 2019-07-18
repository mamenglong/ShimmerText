package com.example.test

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_launcher.*

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
            if (info.id.contains("com.example.test.service.MyAccessibilityService")) {
                return true
            }
        }
        return false
    }
    //打开系统无障碍设置界面

}
