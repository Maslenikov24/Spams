package com.graduate.spams.core.extensions

import com.graduate.spams.model.common.ApiResponse
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