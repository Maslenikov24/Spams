package com.univer.mvvm_coroutines_toothpick_room.model.search.net.service

import com.univer.mvvm_coroutines_toothpick_room.data.number.net.PhoneNumberResponse
import com.univer.mvvm_coroutines_toothpick_room.model.common.ApiMethod
import com.univer.mvvm_coroutines_toothpick_room.model.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

	@GET(ApiMethod.Number.searchNumber)
	suspend fun searchNumber(
		@Query("number") number : String
	) : ApiResponse<PhoneNumberResponse>
}