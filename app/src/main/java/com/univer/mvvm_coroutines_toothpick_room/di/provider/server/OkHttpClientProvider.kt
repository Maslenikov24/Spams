package com.univer.mvvm_coroutines_toothpick_room.di.provider.server

import com.github.stephanenicolas.toothpick.smoothie_lifecycle_ktp.BuildConfig
import com.univer.mvvm_coroutines_toothpick_room.ErrorResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

	override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
		connectTimeout(30, TimeUnit.SECONDS)
		readTimeout(30, TimeUnit.SECONDS)

		addNetworkInterceptor(ErrorResponseInterceptor())
		if (BuildConfig.DEBUG) {
			addNetworkInterceptor(
				HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
			)
		}
		build()
	}
}