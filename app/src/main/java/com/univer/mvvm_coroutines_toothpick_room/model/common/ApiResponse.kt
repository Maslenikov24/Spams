package com.univer.mvvm_coroutines_toothpick_room.model.common

data class ApiResponse<T>(
	var success : Boolean,
	var data : T
)