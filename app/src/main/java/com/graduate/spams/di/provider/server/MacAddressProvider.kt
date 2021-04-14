package com.graduate.spams.di.provider.server

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

class MacAddressProvider @Inject constructor(
	private val context: Context
) : Provider<String> {

	@SuppressLint("HardwareIds")
	override fun get(): String {
		val manager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
		return manager.connectionInfo.macAddress.toUpperCase(Locale.getDefault())
	}
}