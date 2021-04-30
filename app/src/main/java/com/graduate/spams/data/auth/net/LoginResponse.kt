package com.graduate.spams.data.auth.net

import com.squareup.moshi.Json

data class LoginResponse(
	@Json(name = "sessionToken") val sessionToken: String
)
