package com.graduate.spams.data.number.net

import com.squareup.moshi.Json
import java.util.*

data class PhoneNumberResponse(
	@Json(name = "id") val id: String,
	@Json(name = "number") val number: String,
	@Json(name = "rating") val rating: Int,
	@Json(name = "country") val country: String? = null,
	@Json(name = "region") val region: String? = null,
	@Json(name = "operator") val operator: String? = null,
	@Json(name = "organization") val organization: String? = null,
	@Json(name = "type") val type: String? = null,
	@Json(name = "countOfReport") val countOfReport: Int? = null
)
