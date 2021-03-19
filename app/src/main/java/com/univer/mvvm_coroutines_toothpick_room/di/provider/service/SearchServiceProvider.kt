package com.univer.mvvm_coroutines_toothpick_room.di.provider.service

import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class SearchServiceProvider @Inject constructor(
	private val retrofit: Retrofit
) : Provider<SearchService>{

	override fun get(): SearchService = retrofit.create(SearchService::class.java)
}