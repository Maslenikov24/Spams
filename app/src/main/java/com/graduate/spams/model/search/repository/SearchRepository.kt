package com.graduate.spams.model.search.repository

import com.graduate.spams.data.history.db.HistoryEntity
import com.graduate.spams.data.history.domain.HistoryNumber
import com.graduate.spams.data.number.domain.PhoneNumber
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
	suspend fun searchNumber(number: String) : PhoneNumber?
	fun getHistory() : Flow<List<HistoryNumber>>
	suspend fun saveToHistory(data: HistoryEntity)
	suspend fun deleteFromHistory(id: Long)
	suspend fun deleteAllHistory()
}