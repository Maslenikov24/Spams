package com.graduate.spams.model.search.repository

import com.graduate.spams.core.extensions.fetchData
import com.graduate.spams.data.history.db.HistoryDao
import com.graduate.spams.data.history.db.HistoryEntity
import com.graduate.spams.data.history.domain.HistoryNumber
import com.graduate.spams.data.history.mapper.HistoryEntityToDomainMapper
import com.graduate.spams.data.history.mapper.HistoryResponseToEntityMapper
import com.graduate.spams.data.number.domain.PhoneNumber
import com.graduate.spams.data.number.mapper.PhoneNumberResponseToDomainMapper
import com.graduate.spams.di.IO
import com.graduate.spams.model.search.net.service.SearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val searchService: SearchService,
	private val historyDao: HistoryDao,
	private val historyResponseToEntityMapper: HistoryResponseToEntityMapper,
	private val historyEntityToDomainMapper: HistoryEntityToDomainMapper,
	private val phoneNumberResponseToDomainMapper: PhoneNumberResponseToDomainMapper,
	@IO private val ioDispatcher: CoroutineDispatcher
): SearchRepository {

	override suspend fun searchNumber(number: String): PhoneNumber? {
		var item: PhoneNumber? = null
		withContext(ioDispatcher){
			val result = searchService.searchNumber(number).fetchData()
			Timber.tag("AppLog").d(result.toString())
			result?.let {
				saveToHistory(historyResponseToEntityMapper.map(it))
				item = phoneNumberResponseToDomainMapper.map(result)
			}
		}
		return item
	}

	override fun getHistory() : Flow<List<HistoryNumber>> =
		historyDao.getHistory().map { it ->
			it.map { item ->
				historyEntityToDomainMapper.map(item)
			}
		}.flowOn(ioDispatcher)

	override suspend fun saveToHistory(data: HistoryEntity) {
		historyDao.insert(data)
	}

	override suspend fun deleteFromHistory(id: Long) {
		withContext(ioDispatcher){
			historyDao.delete(id)
		}
	}

	override suspend fun deleteAllHistory() {
		withContext(ioDispatcher){
			historyDao.deleteAll()
		}
	}


}