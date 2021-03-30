package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.domain.history.HistoryNumber
import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SearchRepository {
	suspend fun searchNumber(number: String)
	fun getHistory() : Flow<List<HistoryNumber>>
	suspend fun saveToHistory(data: HistoryEntity)
	suspend fun deleteFromHistory(id: Long)
	suspend fun deleteAllHistory()
}