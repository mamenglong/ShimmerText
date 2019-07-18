package com.example.test.mvp

interface IUserView{
    fun setUserName(userName: String)
    fun setSex(sex: String)
    fun error(errormsg: String)
}