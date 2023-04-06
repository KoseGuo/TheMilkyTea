package com.kose.frontpage.ui


import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kose.annotation.BindActivity
import com.kose.common.base.ui.BaseActivity
import com.kose.common.utils.LogUtils
import com.kose.frontpage.R
import com.kose.frontpage.databinding.ActivityMainBinding
import com.kose.frontpage.viewmodel.MainPageViewModel
import com.kose.router.IRouter

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/5 15:20
 */
@BindActivity(path = IRouter.HOMEPAGE)
class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewModel: MainPageViewModel

    override fun initView() {
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainPageViewModel::class.java)
        viewModel.createItemSelect(R.id.fl_container, supportFragmentManager, this)
        getDataBinding().bnvBottom.setOnNavigationItemSelectedListener(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewModel.item.value = item.itemId
        return true
    }
}