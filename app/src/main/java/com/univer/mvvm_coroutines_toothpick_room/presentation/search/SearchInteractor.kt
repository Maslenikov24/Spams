package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.univer.mvvm_coroutines_toothpick_room.data.history.domain.HistoryNumber
import com.univer.mvvm_coroutines_toothpick_room.data.number.domain.PhoneNumber
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {
	suspend fun searchNumber(number: String): PhoneNumber?
	fun getHistory(): Flow<List<HistoryNumber>>
	suspend fun deleteFromHistory(id: Long)
	suspend fun deleteAllHistory()
}