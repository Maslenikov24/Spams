package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.data.history.db.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.history.domain.HistoryNumber
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
	suspend fun searchNumber(number: String)
	fun getHistory() : Flow<List<HistoryNumber>>
	suspend fun saveToHistory(data: HistoryEntity)
	suspend fun deleteFromHistory(id: Long)
	suspend fun deleteAllHistory()
}