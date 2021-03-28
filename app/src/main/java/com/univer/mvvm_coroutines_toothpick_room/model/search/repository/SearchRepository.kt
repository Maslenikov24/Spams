package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber

interface SearchRepository {
	suspend fun searchNumber(number: String)
	suspend fun getHistory() : List<PhoneNumber>
	suspend fun saveToHistory(data: HistoryEntity)
	suspend fun deleteFromHistory(number: String)
}