package com.kose.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.RequiresApi
import com.kose.common.utils.LogUtils
import dalvik.system.DexFile
import kotlin.Exception

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/6 15:23
 */
class KRouter private constructor() {
    private val TAG: String = KRouter::class.java.simpleName
    private val buildPath: String = "com.kose.utils"
    private lateinit var mContext: Context
    private lateinit var map: HashMap<String, Class<out Activity>>

    companion object {
        val instance : KRouter by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            KRouter()
        }
    }

    fun init(context: Context) {
        mContext = context
        map = HashMap()
        val list = getClassNameList()
        list.forEach {
            try {
                val clz = Class.forName(it)
                if (IRouter::class.java.isAssignableFrom(clz)) {
                    LogUtils.d("clz was ${clz.simpleName}")
                    val router = clz.newInstance() as IRouter
                    router.putActivity()
                }
            } catch (e: ClassNotFoundException) {
                LogUtils.e(TAG, "Can't found the class $it")
            } catch (e: Exception) {
                LogUtils.e(TAG, "Can't create the class")
            }
        }
    }

    private fun getClassNameList(): ArrayList<String> {
        val list = ArrayList<String>()
        try {
            val apkPath = mContext.packageManager.getApplicationInfo(mContext.packageName, 0).sourceDir
            LogUtils.d(TAG, "sourceDir == $apkPath")
            val dexFile = DexFile(apkPath)
            val elements = dexFile.entries()
            while (elements.hasMoreElements()) {
                val name = elements.nextElement()
                if (name.contains(buildPath)) {
                    list.add(name)
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            LogUtils.e(TAG, "Can't found the package")
        } catch (e: Exception) {
            LogUtils.e(TAG, "find the class error")
        }
        return list
    }

    fun addActivity(key: String, clz: Class<out Activity>?) {
        if (!TextUtils.isEmpty(key) && clz != null && !map.containsKey(key)) {
            LogUtils.d("clz was ${clz.simpleName}")
            map[key] = clz
        }
    }

    fun startActivity(target: String) {
        val clz = map[target]
        val intent = Intent()
        if (clz != null) {
            intent.setClass(mContext, clz)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(intent)
        }
    }

    fun startActivity(target: String, bundle: Bundle) {
        val clz = map[target]
        val intent = Intent()
        if (clz != null) {
            intent.setClass(mContext, clz)
            intent.putExtra(target, bundle)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(intent)
        }
    }
}