package com.kose.common.utils

import android.nfc.Tag
import android.util.Log
import com.kose.common.BuildConfig

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/6 15:28
 */
object LogUtils {
    private const val DEFAULT_TAG: String = "MilkyTea_"
    private val isDebug = BuildConfig.DEBUG

    fun e(tag: String, msg: String) {
        if (isDebug) {
            Log.e(DEFAULT_TAG + tag, msg)
        }
    }

    fun e(msg: String) {
        if (isDebug) {
            Log.e(DEFAULT_TAG, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(DEFAULT_TAG + tag, msg)
        }
    }

    fun d(msg: String) {
        if (isDebug) {
            Log.d(DEFAULT_TAG, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (isDebug) {
            Log.i(DEFAULT_TAG + tag, msg)
        }
    }

    fun i(msg: String) {
        if (isDebug) {
            Log.i(DEFAULT_TAG, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (isDebug) {
            Log.w(DEFAULT_TAG + tag, msg)
        }
    }

    fun w(msg: String) {
        if (isDebug) {
            Log.w(DEFAULT_TAG, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (isDebug) {
            Log.v(DEFAULT_TAG + tag, msg)
        }
    }

    fun v(msg: String) {
        if (isDebug) {
            Log.v(DEFAULT_TAG, msg)
        }
    }
}