package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.domain.PhoneNumber

interface SearchRepository {
	suspend fun getNumber(number: String)
	suspend fun getHistory() : List<PhoneNumber>
	suspend fun saveToHistory(number: String)
	suspend fun deleteFromHistory(number: String)
}