package com.univer.mvvm_coroutines_toothpick_room.di.provider.server

import com.github.stephanenicolas.toothpick.smoothie_lifecycle_ktp.BuildConfig
import com.univer.mvvm_coroutines_toothpick_room.ErrorResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

	val logger = HttpLoggingInterceptor.Logger { message -> Timber.v(message) }

	val TIMEOUT = 7L

	private val logging = HttpLoggingInterceptor(logger).apply {
		level = HttpLoggingInterceptor.Level.BODY
	}

	override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
		connectTimeout(TIMEOUT, TimeUnit.SECONDS)
		readTimeout(TIMEOUT, TimeUnit.SECONDS)
		addNetworkInterceptor(ErrorResponseInterceptor())
		addInterceptor (
			HttpLoggingInterceptor(logger).apply {
				level = HttpLoggingInterceptor.Level.BODY
			}
		)
		build()
	}
}