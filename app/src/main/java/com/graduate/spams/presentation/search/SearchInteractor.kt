package com.graduate.spams.presentation.search

import com.graduate.spams.data.history.domain.HistoryNumber
import com.graduate.spams.data.number.domain.PhoneNumber
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
	suspend fun searchNumber(number: String): PhoneNumber?
	fun getHistory(): Flow<List<HistoryNumber>>
	suspend fun deleteFromHistory(id: Long)
	suspend fun deleteAllHistory()
}