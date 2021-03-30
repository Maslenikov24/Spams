package com.univer.mvvm_coroutines_toothpick_room.data.history.domain

data class HistoryNumber(
	val id: Long,
	val number: String,
	val date: Long,
	val rating: Int? = 0
)
