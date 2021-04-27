package com.graduate.spams.di.provider.server

import com.graduate.spams.di.provider.AuthInterceptor
import com.graduate.spams.di.provider.ErrorResponseInterceptor
import com.graduate.spams.model.preferences.app.AppPreferenceStorage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(
	private val appPreferenceStorage: AppPreferenceStorage
) : Provider<OkHttpClient> {

	private val logger = HttpLoggingInterceptor.Logger { message -> Timber.tag("AppLog").v(message) }

	private val TIMEOUT = 10L

	override fun get(): OkHttpClient = with(OkHttpClient.Builder()) {
		connectTimeout(TIMEOUT, TimeUnit.SECONDS)
		readTimeout(TIMEOUT, TimeUnit.SECONDS)
		addNetworkInterceptor(AuthInterceptor(appPreferenceStorage))
		addNetworkInterceptor(ErrorResponseInterceptor())
		addInterceptor (
			HttpLoggingInterceptor(logger).apply {
				level = HttpLoggingInterceptor.Level.BODY
			}
		)
		build()
	}
}