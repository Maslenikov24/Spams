package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.core.extensions.fetchData
import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryDao
import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.domain.history.HistoryNumber
import com.univer.mvvm_coroutines_toothpick_room.data.mapper.history.HistoryResponseToEntityMapper
import com.univer.mvvm_coroutines_toothpick_room.di.IO
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
	private val searchService: SearchService,
	private val historyDao: HistoryDao, //TODO: refactor to history
	private val historyResponseToEntityMapper: HistoryResponseToEntityMapper,
	@IO private val ioDispatcher: CoroutineDispatcher
): SearchRepository {

	override suspend fun searchNumber(number: String) {
		withContext(ioDispatcher){
			val result = searchService.searchNumber(number).fetchData()
			Timber.d(result.toString())
			result?.let {
				saveToHistory(historyResponseToEntityMapper.map(it))
			}
		}
	}

	override fun getHistory() : Flow<List<HistoryNumber>> =
		historyDao.getHistory().map { it ->
			it.map { item ->
				HistoryNumber(id = item.id, number = item.number, date = item.date, rating = item.rating) // Todo: mapper
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