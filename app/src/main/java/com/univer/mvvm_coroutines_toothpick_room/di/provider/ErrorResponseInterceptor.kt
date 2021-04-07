package com.univer.mvvm_coroutines_toothpick_room.di.provider

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class ErrorResponseInterceptor: Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val response = chain.proceed(chain.request())

		val code = response.code

		if (code in 400..500) {
			Timber.tag("AppLog").e("Server error is $code")
			//throw ServerError(code)
		}

		return response
	}
}