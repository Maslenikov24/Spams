package com.univer.mvvm_coroutines_toothpick_room.data.domain.number

import java.util.*

data class PhoneNumber(
	val id: String,
	val number: String,
	val isBan: Int,
	val country: String? = null,
	val region: String? = null,
	val operator: String? = null,
	val organization: String? = null,
	val type: String? = null,
	val danger: Int? = null,
	val lastCallDate: Date? = null,
	val countOfReport: Int? = null
)