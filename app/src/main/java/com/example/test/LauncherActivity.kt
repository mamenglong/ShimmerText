package com.example.test

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {

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
            }
            mainView.addView(btn)
        }

    }
}
