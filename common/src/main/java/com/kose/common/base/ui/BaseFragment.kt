package com.kose.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/6 20:53
 */
abstract class BaseFragment<DB: ViewDataBinding> : Fragment() {
    private lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        initView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
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

}