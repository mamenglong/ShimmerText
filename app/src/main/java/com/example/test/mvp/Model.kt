package com.example.test.mvp

import org.litepal.LitePal

interface IUserModel {
    fun saveUser(user: User)
    fun readUser(name:String):User
}
class UserModel :IUserModel{

    override fun saveUser(user: User) {
        println(user)
        user.save()
    }

    override fun readUser(name: String): User {
        val user=LitePal.where("name=?",name).find(User::class.java)
        println(user.toList())
        if (user.size>0)
        return user[0]
        else
            return User("m默认","",0)
    }

}