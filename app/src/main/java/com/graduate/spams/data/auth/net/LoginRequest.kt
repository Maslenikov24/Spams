package com.graduate.spams.data.auth.net

import com.squareup.moshi.Json

data class LoginRequest(
	@Json(name = "uid") val uid: String,
	@Json(name = "registrationToken") val registrationToken: String
)
