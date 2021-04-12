package com.graduate.spams.di.provider.server

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.graduate.spams.di.ApiPath
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
	@ApiPath private val apiPath: String,
	private val okHttpClient: OkHttpClient,
	private val moshiConverterFactory: MoshiConverterFactory
): Provider<Retrofit> {

	override fun get(): Retrofit = Retrofit.Builder()
		.baseUrl(apiPath)
			.client(okHttpClient)
			.addConverterFactory(moshiConverterFactory)
			//.addCallAdapterFactory(CoroutineCallAdapterFactory()) TODO: deprecated
			.build()
}