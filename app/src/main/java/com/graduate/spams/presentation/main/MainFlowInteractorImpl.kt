package com.graduate.spams.presentation.main

import com.graduate.spams.model.notification.NotificationRepository
import javax.inject.Inject

class MainFlowInteractorImpl @Inject constructor(
	private val notificationRepository: NotificationRepository
) : MainFlowInteractor {

	override suspend fun initToken() = notificationRepository.initToken()
}