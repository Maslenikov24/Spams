package com.graduate.spams.presentation.main

interface MainFlowInteractor {
	suspend fun initToken()
	suspend fun login()
}