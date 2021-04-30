package com.graduate.spams.model.connect

interface ConnectRepository {
	suspend fun inviteChild(uid: String)
	suspend fun acceptParent(uid: String)
}