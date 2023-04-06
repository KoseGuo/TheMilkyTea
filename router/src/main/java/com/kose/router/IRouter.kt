package com.kose.router

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/6 15:42
 */
interface IRouter {
    companion object {
        const val HOMEPAGE = "/front_page/main_activity"
        const val LOGINPAGE = "/user_center/login_activity"
    }
    fun putActivity()
}