package com.example.test.mvp

import org.litepal.crud.LitePalSupport
import org.litepal.exceptions.DataSupportException

data class User(var name:String,var sex:String,var age:Int):LitePalSupport()