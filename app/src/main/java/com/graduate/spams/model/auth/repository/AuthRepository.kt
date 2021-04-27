package com.graduate.spams.model.auth.repository

interface AuthRepository {
	suspend fun initToken()
	suspend fun sendToken(token: String)
	suspend fun login()
}