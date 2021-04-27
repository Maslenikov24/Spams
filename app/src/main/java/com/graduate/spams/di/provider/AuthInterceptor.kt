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
				.build()
		} ?: chain.request()
		return chain.proceed(request)
	}
}