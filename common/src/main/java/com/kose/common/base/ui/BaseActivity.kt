package com.kose.common.base.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @Description Activity基类
 * @Author KoseGuo
 * @Since 2023/4/3 23:58
 */
abstract class BaseActivity<DB: ViewDataBinding> : AppCompatActivity() {
    private lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor()
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        initView()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        refreshViewAndModel()
    }

    /**
     * 初始化方法
     */
    abstract fun initView()

    /**
     * 获取布局ID
     */
    abstract fun getLayoutId() : Int

    /**
     * 更新视图与数据
     */
    open fun refreshViewAndModel() {}

    /**
     * 获取DataBinding实例类
     */
    protected fun getDataBinding() : DB {
        return binding
    }

    private fun setStatusBarColor() {
        val decorView = window.decorView
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}