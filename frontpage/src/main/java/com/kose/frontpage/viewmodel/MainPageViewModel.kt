package com.kose.frontpage.viewmodel

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kose.common.utils.LogUtils
import com.kose.frontpage.R
import com.kose.frontpage.ui.fragment.HomeFragment
import com.kose.frontpage.ui.fragment.MineFragment
import com.kose.frontpage.ui.fragment.MsgFragment

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/6 19:01
 */
class MainPageViewModel() : ViewModel() {
    private val TAG = MainPageViewModel::class.java.simpleName

    companion object {
        const val HOME_PAGE = "home_page"
        const val MSG_PAGE = "msg_page"
        const val MINE_PAGE = "mine_page"
    }

    private var fromTarget = HOME_PAGE
    val item: MutableLiveData<Int> = MutableLiveData()

    fun createItemSelect(
        containerId: Int,
        manager: FragmentManager,
        lifecycleOwner: LifecycleOwner
    ) {
        val beginTransaction = manager.beginTransaction()
        beginTransaction.add(containerId, HomeFragment(), HOME_PAGE)
        beginTransaction.commitAllowingStateLoss()

        item.observe(lifecycleOwner, Observer {
            LogUtils.e(TAG, "change item was $it")
            val transaction = manager.beginTransaction()
            val fromFragment = manager.findFragmentByTag(fromTarget)
            val toTarget = when (it) {
                R.id.item_home -> HOME_PAGE
                R.id.item_msg -> MSG_PAGE
                R.id.item_user -> MINE_PAGE
                else -> ""
            }
            if (!toTarget.equals(fromTarget)) {
                var toFragment = manager.findFragmentByTag(toTarget)
                if (fromFragment != null) {
                    LogUtils.d(TAG, "change page was ${fromFragment.javaClass.simpleName}")
                    transaction.hide(fromFragment)
                }
                if (toFragment == null) {
                    toFragment = when (toTarget) {
                        MSG_PAGE -> MsgFragment()
                        MINE_PAGE -> MineFragment()
                        else -> HomeFragment()
                    }
                    transaction.add(containerId, toFragment, toTarget)
                    LogUtils.e(TAG, "the fragment wasn't added")
                } else {
                    LogUtils.e(TAG, "the fragment was added")
                    transaction.show(toFragment)
                }
                transaction.commitAllowingStateLoss()
                fromTarget = toTarget
            }
        })
    }
}