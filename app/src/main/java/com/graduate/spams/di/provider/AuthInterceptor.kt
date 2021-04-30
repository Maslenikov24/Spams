package com.graduate.spams.di.provider

import com.graduate.spams.model.preferences.app.AppPreferenceStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
	private val appPreferenceStorage: AppPreferenceStorage
) : Interceptor
{
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = appPreferenceStorage.sessionToken?.let{
			chain.request()
				.newBuilder()
				.addHeader("Authorization", it)
				.addHeader("Content-Type", "application/json; charset=utf-8")
				.build()
		} ?: chain.request()
		return chain.proceed(request)
	}
}