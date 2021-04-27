package com.graduate.spams.presentation.main

import com.graduate.spams.model.auth.repository.AuthRepository
import javax.inject.Inject

class MainFlowInteractorImpl @Inject constructor(
	private val authRepository: AuthRepository
) : MainFlowInteractor {

	override suspend fun initToken() = authRepository.initToken()

	override suspend fun login() = authRepository.login()

}