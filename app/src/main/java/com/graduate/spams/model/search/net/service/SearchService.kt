package com.graduate.spams.model.search.net.service

import com.graduate.spams.data.number.net.PhoneNumberResponse
import com.graduate.spams.model.common.ApiMethod
import com.graduate.spams.model.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

	@GET(ApiMethod.Number.searchNumber)
	suspend fun searchNumber(
		@Query("number") number : String
	) : ApiResponse<PhoneNumberResponse>
}