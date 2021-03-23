package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryDao
import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	//private val searchService: SearchService,
	private val historyDao: HistoryDao //TODO: refactor to history
): SearchRepository {

	override suspend fun getNumber(number: String) {
		//searchService.getNumber(number)
		saveToHistory(number) //TODO: save Number
	}

	override suspend fun getHistory() : List<PhoneNumber> {
		val result = historyDao.getHistory()
		return result.map { PhoneNumber("", it.number, 0) }
	}

	override suspend fun saveToHistory(number: String) {
		historyDao.insert(HistoryEntity(number)) //TODO: delete from import PhoneNumberEntity
	}

	override suspend fun deleteFromHistory(number: String) {

	}


}