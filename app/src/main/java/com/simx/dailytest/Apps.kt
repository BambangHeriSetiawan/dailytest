package com.simx.dailytest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class Apps: Application() {
    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

    }
}