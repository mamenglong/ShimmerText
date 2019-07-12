package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.test.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class DataBingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val databing:ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        val user=User()
        user.apply {
            name.set("long")
            sex.set("boy")
        }
        databing.user=user
            databing.update.setOnClickListener {
                user.name.set("menglong${Random().nextInt()}" )
                update.text="刷新${user.name.get()}"
        }
    }
}
