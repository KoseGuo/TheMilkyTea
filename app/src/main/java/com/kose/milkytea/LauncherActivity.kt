package com.kose.milkytea


import android.os.Handler
import com.kose.common.base.ui.BaseActivity
import com.kose.milkytea.databinding.ActivityLauncherBinding
import com.kose.router.IRouter
import com.kose.router.KRouter

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/5 15:20
 */
class LauncherActivity : BaseActivity<ActivityLauncherBinding>() {

    override fun initView() {
        Handler().postDelayed({
            KRouter.instance.startActivity(IRouter.HOMEPAGE)
        }, 3000)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_launcher
    }
}