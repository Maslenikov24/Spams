package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import com.univer.mvvm_coroutines_toothpick_room.model.common.ApiResponse
import timber.log.Timber
import java.lang.RuntimeException

fun<T> ApiResponse<T>.fetchData() : T? =
	when {
		this.success -> this.data
		else -> {
			Timber.e("fetchData error")
			null //throw RuntimeException()
		}
	}