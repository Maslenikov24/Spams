package com.graduate.spams.data.history.domain

data class HistoryNumber(
	val id: Long,
	val number: String,
	val date: Long,
	val rating: Int? = 0
)
