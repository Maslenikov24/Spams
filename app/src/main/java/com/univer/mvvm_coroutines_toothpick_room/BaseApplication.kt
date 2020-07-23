package com.univer.mvvm_coroutines_toothpick_room

import android.app.Application
import com.univer.mvvm_coroutines_toothpick_room.di.AppScope
import com.univer.mvvm_coroutines_toothpick_room.di.appModule
import com.univer.mvvm_coroutines_toothpick_room.di.navigationModule
import timber.log.Timber
import toothpick.Scope
import toothpick.ktp.KTP

class BaseApplication: Application() {

    private lateinit var rootScope: Scope

    override fun onCreate() {
        super.onCreate()
        initTimber()
        rootScope = KTP.openScope(AppScope::class.java)
            .installModules(appModule(this), navigationModule())
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}