package com.graduate.spams.entry

import android.app.Application
import com.graduate.spams.BuildConfig
import com.graduate.spams.di.Scopes
import com.graduate.spams.di.module.appModule
import com.graduate.spams.di.module.navigationModule
import com.graduate.spams.di.module.roomModule
import timber.log.Timber
import toothpick.Scope
import toothpick.configuration.Configuration
import toothpick.ktp.KTP

class App: Application() {

    private lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()
        initTimber()
        Timber.tag("AppLog").d("AppInit")
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
                navigationModule(),
                roomModule(this)
            )
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        appScope.release()
    }
}