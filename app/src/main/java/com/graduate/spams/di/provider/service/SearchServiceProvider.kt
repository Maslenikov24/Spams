package com.graduate.spams.di.provider.service

import com.graduate.spams.model.search.net.service.SearchService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class SearchServiceProvider @Inject constructor(
	private val retrofit: Retrofit
) : Provider<SearchService>{

	override fun get(): SearchService = retrofit.create(SearchService::class.java)
}