package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber

interface SearchInteractor {
	suspend fun searchNumber(number: String)
	suspend fun getHistory(): List<PhoneNumber>
}