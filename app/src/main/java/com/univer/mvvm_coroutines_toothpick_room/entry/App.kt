package com.univer.mvvm_coroutines_toothpick_room.entry

import android.app.Application
import com.univer.mvvm_coroutines_toothpick_room.BuildConfig
import com.univer.mvvm_coroutines_toothpick_room.di.Scopes
import com.univer.mvvm_coroutines_toothpick_room.di.module.appModule
import com.univer.mvvm_coroutines_toothpick_room.di.module.navigationModule
import timber.log.Timber
import toothpick.Scope
import toothpick.configuration.Configuration
import toothpick.ktp.KTP

class App: Application() {

    private lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initToothpick()
        initAppScope()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initToothpick(){
        if (BuildConfig.DEBUG){
            KTP.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else{
            KTP.setConfiguration(Configuration.forProduction())
        }
    }

    private fun initAppScope(){
        appScope = KTP.openScope(Scopes.APP_SCOPE)
            .installModules(
                appModule(this),
                navigationModule()
            )
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        appScope.release()
    }
}