package com.graduate.spams.model.detail.repository

import com.graduate.spams.core.extensions.fetchData
import com.graduate.spams.data.number.domain.PhoneNumber
import com.graduate.spams.data.number.mapper.PhoneNumberResponseToDomainMapper
import com.graduate.spams.di.IO
import com.graduate.spams.model.search.net.service.SearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DeatilRepositoryImpl @Inject constructor(
	private val searchService: SearchService,
	private val phoneNumberResponseToDomainMapper: PhoneNumberResponseToDomainMapper,
	@IO private val ioDispatcher: CoroutineDispatcher
): DetailRepository {

	override suspend fun searchNumber(number: String): PhoneNumber? {
		var item: PhoneNumber? = null
		withContext(ioDispatcher){
			val result = searchService.searchNumber(number).fetchData()
			Timber.tag("AppLog").d(result.toString())
			result?.let {
				item = phoneNumberResponseToDomainMapper.map(result)
			}
		}
		return item
	}


}