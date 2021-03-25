package com.univer.mvvm_coroutines_toothpick_room.model.search.net.service

import com.univer.mvvm_coroutines_toothpick_room.data.net.number.PhoneNumberResponse
import com.univer.mvvm_coroutines_toothpick_room.model.common.ApiMethod
import com.univer.mvvm_coroutines_toothpick_room.model.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

	@GET(ApiMethod.Number.getNumber)
	suspend fun getNumber(
		@Query("number") number : String
	) : ApiResponse<PhoneNumberResponse>
}