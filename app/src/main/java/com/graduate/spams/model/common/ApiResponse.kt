package com.graduate.spams.model.common

data class ApiResponse<T>(
	val success : Boolean,
	val errorCode: Int,
	val data : T?
)