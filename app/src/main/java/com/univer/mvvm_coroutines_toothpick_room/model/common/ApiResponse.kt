package com.univer.mvvm_coroutines_toothpick_room.model.common

data class ApiResponse<T>(
	val success : Boolean,
	val errorCode: Int,
	val data : T?
)