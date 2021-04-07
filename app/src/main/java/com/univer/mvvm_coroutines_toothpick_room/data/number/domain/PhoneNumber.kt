package com.univer.mvvm_coroutines_toothpick_room.data.number.domain

data class PhoneNumber(
	val id: String,
	val number: String,
	val country: String? = null,
	val region: String? = null,
	val operator: String? = null,
	val organization: String? = null,
	val type: String? = null,
	val rating: Int? = null,
	val lastCallDate: Long? = null,
	val countOfReport: Int? = null
)