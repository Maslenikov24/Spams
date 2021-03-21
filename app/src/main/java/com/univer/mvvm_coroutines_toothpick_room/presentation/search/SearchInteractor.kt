package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.domain.PhoneNumber

interface SearchInteractor {
	suspend fun getNumber(number: String)
	suspend fun getHistory(): List<PhoneNumber>
}