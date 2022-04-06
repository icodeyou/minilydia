package com.example.minilydia

import android.app.Application
import android.content.Context
import com.example.minilydia.data.di.dataModule
import com.example.minilydia.data.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@AndroidApplication)
            modules(listOf(dataModule, viewModelsModule))
        }
    }

    companion object {
        lateinit var mInstance: AndroidApplication
        fun getContext(): Context? {
            return mInstance.applicationContext
        }
    }
}
