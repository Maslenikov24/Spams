package com.graduate.spams.model.connect

import com.graduate.spams.di.IO
import com.graduate.spams.model.connect.net.service.ConnectService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ConnectRepositoryImpl @Inject constructor(
	private val connectService: ConnectService,
	@IO private val ioDispatcher: CoroutineDispatcher,
) : ConnectRepository {

	override suspend fun inviteChild(uid: String) {
		withContext(ioDispatcher){
			connectService.addChild(uid)
			Timber.tag("AppLog").d("Invite success")
			//notify about success
		}
	}

	override suspend fun acceptParent(uid: String) {
		withContext(ioDispatcher){
			connectService.addParent(uid)
			Timber.tag("AppLog").d("Accept success")
		}
	}
}