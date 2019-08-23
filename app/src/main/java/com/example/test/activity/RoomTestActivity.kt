package com.example.test.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.R
import com.example.test.db.TestDatabase
import com.example.test.db.User
import com.example.test.db.Wife
import com.example.test.db.WorkInfo
import kotlinx.android.synthetic.main.activity_room_test.*
import kotlin.random.Random
import kotlin.reflect.KProperty

class RoomTestActivity : AppCompatActivity() {
    lateinit var database:TestDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_test)
        database=TestDatabase.getInstance(this)

        database.userDao().insert(User("梦龙","男",23))

        initView()
    }

    private fun initView() {
        btn_delete.setOnClickListener {
            database.userDao().delete(User("梦龙","男",23))
        }
        btn_insert.setOnClickListener {
            Log.i("RoomTestActivity","btn_insert.setOnClickListener")
            database.userDao().insert(User("梦龙","男", Random(System.currentTimeMillis()).nextInt(1000)))
            database.workInfoDao().insert(WorkInfo(where = "ss"+Random(System.currentTimeMillis()).nextInt(),status = "44",id = 6))
            database.wifeDao().insert(Wife().setName("kjkj"))
            Log.i("RoomTestActivity","btn_insert.setOnClickListener     end")

        }
        database.userDao().getAll().observeForever {
            test.text=it.toList().toString()

        }
           database.wifeDao().getAll().observeForever {
               test1.text=it.toList().toString()
           }
    }
}

