package com.graduate.spams.model.common

import com.squareup.moshi.Json

data class ApiResponse<T>(
	@Json(name = "success") val success : Boolean,
	@Json(name = "errorCode") val errorCode: Int,
	@Json(name = "data") val data : T?
)