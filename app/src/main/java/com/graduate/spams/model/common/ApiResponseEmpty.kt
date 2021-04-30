package com.graduate.spams.model.common

import com.squareup.moshi.Json

data class ApiResponseEmpty(
	@Json(name = "success") val success : Boolean?,
	@Json(name = "errorCode") val errorCode: Int?
)