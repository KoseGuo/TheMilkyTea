package com.kose.milkytea.app

import android.app.Application
import com.kose.common.utils.LogUtils
import com.kose.router.KRouter

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/5 15:20
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d("application onCreate")
        KRouter.instance.init(this)
    }

}