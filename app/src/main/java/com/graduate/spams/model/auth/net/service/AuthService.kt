package com.graduate.spams.model.auth.net.service

import com.graduate.spams.data.auth.net.AuthResponse
import com.graduate.spams.model.common.ApiMethod
import com.graduate.spams.model.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {

	@GET(ApiMethod.updateToken)
	suspend fun sendToken(
		@Query("registrationToken") token: String,
		@Query("macAddress") macAddress: String
	): ApiResponse<AuthResponse>
}