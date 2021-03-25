package com.univer.mvvm_coroutines_toothpick_room.di.provider.server

import com.github.stephanenicolas.toothpick.smoothie_lifecycle_ktp.BuildConfig
import com.univer.mvvm_coroutines_toothpick_room.ErrorResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

	/*val logger = object : Logger(){
		override fun log
	}*/

	val TIMEOUT = 3L

	private val logging = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BASIC
	}

	override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
		connectTimeout(TIMEOUT, TimeUnit.SECONDS)
		readTimeout(TIMEOUT, TimeUnit.SECONDS)

		addNetworkInterceptor(ErrorResponseInterceptor())
		if (BuildConfig.DEBUG) {
			addInterceptor(logging)
		}
		build()
	}
}