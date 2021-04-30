package com.graduate.spams.entry

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
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

    companion object {
        private const val DEFAULT_NOTIFICATION_CHANNEL_ID = "${BuildConfig.APPLICATION_ID}.DEFAULT"
    }

    private lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()
        initNotificationsChannel()
        initTimber()
        Timber.tag("AppLog").d("AppInit")
        initToothpick()
        initAppScope()
    }

    private fun initNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val name = "Notifications"
            val description = "User notifications"

            NotificationChannel(DEFAULT_NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH).apply {
                this.description = description
                this.enableLights(true)
                this.lightColor = Color.RED
                notificationManager.createNotificationChannel(this)
            }
        }
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