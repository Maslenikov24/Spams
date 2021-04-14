package com.graduate.spams.data.auth.net

import com.squareup.moshi.Json

data class AuthResponse(
	@Json(name = "uid") val uid: String,
	@Json(name = "registrationToken") val token: String
)
