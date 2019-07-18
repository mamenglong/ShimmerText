package com.example.test.mvp


class UserPresenter(var mIUserView:IUserView){

    private var mIUserModel: IUserModel=UserModel()
    /**
     * 数据保存
     * @param user
     */
    fun saveUser(user: User) {
        mIUserModel.saveUser(user)
    }

    /**
     * 读取数据
     * @param name
     */
    fun readUser(name: String) {
        val user = mIUserModel.readUser(name)
      println("getread: " + user!!)
        if (user != null) {
            mIUserView.setUserName(user.name)
            mIUserView.setSex(user.sex)
        } else {
            mIUserView.error("没有找到")
        }
    }

}