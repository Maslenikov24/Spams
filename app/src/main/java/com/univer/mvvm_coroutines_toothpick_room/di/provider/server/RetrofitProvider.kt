package com.univer.mvvm_coroutines_toothpick_room.di.provider.server

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
	private val okHttpClient: OkHttpClient

): Provider<Retrofit> {

	override fun get(): Retrofit = Retrofit.Builder()
		.baseUrl("apiPath")
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create())
			.addCallAdapterFactory(CoroutineCallAdapterFactory())
			.build()

}