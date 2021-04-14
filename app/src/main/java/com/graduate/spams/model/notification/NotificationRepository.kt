package com.graduate.spams.model.notification

interface NotificationRepository {
	suspend fun initToken()
	suspend fun sendToken(token: String)
}