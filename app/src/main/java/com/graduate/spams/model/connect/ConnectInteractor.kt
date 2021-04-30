package com.graduate.spams.model.connect

interface ConnectInteractor {
	suspend fun inviteChild(uid: String)
	suspend fun acceptParent(uid: String)
}