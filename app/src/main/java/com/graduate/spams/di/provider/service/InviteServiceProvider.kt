package com.graduate.spams.di.provider.service

import com.graduate.spams.model.connect.net.service.ConnectService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class InviteServiceProvider @Inject constructor(
	private val retrofit: Retrofit
) : Provider<ConnectService>{

	override fun get(): ConnectService = retrofit.create(ConnectService::class.java)
}