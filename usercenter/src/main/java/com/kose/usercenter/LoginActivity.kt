package com.kose.usercenter

import com.kose.annotation.BindActivity
import com.kose.common.base.ui.BaseActivity
import com.kose.router.IRouter
import com.kose.router.KRouter
import com.kose.usercenter.databinding.ActivityLoginBinding

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/5 15:20
 */
@BindActivity(path = IRouter.LOGINPAGE)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    override fun initView() {
    }

    override fun refreshViewAndModel() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}