package com.example.test.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test.R
import com.example.test.mvp.IUserView
import com.example.test.mvp.User
import com.example.test.mvp.UserPresenter
import kotlinx.android.synthetic.main.activity_mvptest.*

class MVPTestActivity : AppCompatActivity(),IUserView {
    lateinit var presenter:UserPresenter
    override fun setUserName(userName: String) {
        println("setUserName  $userName")
        log2.append(userName+"\n")
    }

    override fun setSex(sex: String) {
        println("setSex   $sex")
        log2.append(sex+"\n")
    }

    override fun error(errormsg: String) {
        Toast.makeText(this, errormsg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvptest)
        presenter=UserPresenter(this)

        save.setOnClickListener {
            val user=User(name.text.toString(),sex.text.toString() ,10)
            presenter.saveUser(user)
            log1.append("user $user 保存成功！")
        }
        getResult.setOnClickListener {
            presenter.readUser(get.text.toString())

        }
    }

}
