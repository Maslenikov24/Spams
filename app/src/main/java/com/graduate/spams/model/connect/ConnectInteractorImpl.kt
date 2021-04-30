package com.graduate.spams.model.connect

import javax.inject.Inject

class ConnectInteractorImpl @Inject constructor(
	private val connectRepository: ConnectRepository
): ConnectInteractor {

	override suspend fun inviteChild(uid: String) = connectRepository.inviteChild(uid)
	override suspend fun acceptParent(uid: String) = connectRepository.acceptParent(uid)
}