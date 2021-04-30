package com.graduate.spams.model.connect.net.service

import com.graduate.spams.model.common.ApiMethod
import com.graduate.spams.model.common.ApiResponseEmpty
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectService {

	@GET(ApiMethod.Invite.addChild)
	suspend fun addChild(
		@Query("uid") uid : String
	) : ApiResponseEmpty

	@GET(ApiMethod.Invite.addParent)
	suspend fun addParent(
		@Query("uid") uid : String
	): ApiResponseEmpty
}