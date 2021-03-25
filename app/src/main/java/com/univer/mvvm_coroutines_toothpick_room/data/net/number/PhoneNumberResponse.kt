package com.univer.mvvm_coroutines_toothpick_room.data.net.number

import com.squareup.moshi.Json
import java.util.*

data class PhoneNumberResponse(
	@Json(name = "_id") val id: String,
	@Json(name = "number") val number: String,
	@Json(name = "isBan") val isBan: Int,
	@Json(name = "country") val country: String? = null,
	@Json(name = "region") val region: String? = null,
	@Json(name = "operator") val operator: String? = null,
	@Json(name = "organization") val organization: String? = null,
	@Json(name = "type") val type: String? = null,
	@Json(name = "rating") val danger: Int? = null,
	@Json(name = "lastCallDate") val lastCallDate: Date? = null,
	@Json(name = "countOfReport") val countOfReport: Int? = null
)
