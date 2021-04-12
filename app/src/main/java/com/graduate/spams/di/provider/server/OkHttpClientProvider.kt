package com.graduate.spams.di.provider.server

import com.graduate.spams.di.provider.ErrorResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {

	private val logger = HttpLoggingInterceptor.Logger { message -> Timber.tag("AppLog").v(message) }

	private val TIMEOUT = 10L

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