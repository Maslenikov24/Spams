package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.core.extensions.fetchData
import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryDao
import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.data.mapper.history.HistoryResponseToEntityMapper
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val searchService: SearchService,
	private val historyDao: HistoryDao, //TODO: refactor to history
	private val historyResponseToEntityMapper: HistoryResponseToEntityMapper
): SearchRepository {

	override suspend fun getNumber(number: String) {
		Timber.d("loading getNumber")
		val result = searchService.getNumber(number).fetchData()
		Timber.d("loaded getNumber successfully")
		Timber.d(result.toString())
		result?.let {
			saveToHistory(historyResponseToEntityMapper.map(result))
		}
	}

	override suspend fun getHistory() : List<PhoneNumber> {
		val result = historyDao.getHistory()
		return result.map { PhoneNumber("", it.number, 0) }
	}

	override suspend fun saveToHistory(data: HistoryEntity) {
		historyDao.insert(data) //TODO: delete from import PhoneNumberEntity
	}

	override suspend fun deleteFromHistory(number: String) {

	}


}