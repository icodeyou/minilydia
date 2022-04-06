package com.example.minilydia

import android.app.Application
import android.content.Context
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var mInstance: MyApplication
        fun getContext(): Context? {
            return mInstance.applicationContext
        }
    }
}
