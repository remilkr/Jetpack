package com.sample.sampledemo

import android.app.Application
import android.content.Context
import com.sample.sampledemo.repository.local.roomdb.preference.AppPreference
import com.sample.sampledemo.utils.AppUtil.isNetworkAvailable
import java.lang.ref.WeakReference

class MyApplication : Application() {
    companion object {
        //To access the application context from any class
        lateinit var appContext: WeakReference<Context>
        fun isNetWorkAvailable() = this.appContext.get()?.isNetworkAvailable()
    }

    override fun onCreate() {
        //saves the application context
        appContext = WeakReference(applicationContext)
        AppPreference.shared.initWith(applicationContext)
        super.onCreate()
    }

}