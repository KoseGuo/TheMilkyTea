package com.kose.network

import android.util.Log
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Description
 * @Author KoseGuo
 * @Since 2023/4/4 0:26
 */
class RequestUtil private constructor() {
    private val TAG = RequestUtil.javaClass.simpleName
    private val BASE_URL = "https://www.wanandroid.com"
    private lateinit var retrofit: Retrofit
    private lateinit var map: HashMap<String, Any?>
    companion object {
        val instance : RequestUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RequestUtil()
        }
    }

    init {
        Log.d(TAG, "初始化表")
        map = HashMap()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun <T> getApiService(clazz: Class<T>) : T {
        val name = clazz.canonicalName
        if (!map.containsKey(name)) {
            val clz = retrofit.create(clazz)
            map.put(name, clz)
        }
        return map.get(name) as T
    }
}