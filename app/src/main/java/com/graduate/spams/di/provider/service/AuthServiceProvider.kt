package com.graduate.spams.di.provider.service

import com.graduate.spams.model.auth.net.service.AuthService
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Provider

class AuthServiceProvider @Inject constructor(
	private val retrofit: Retrofit
) : Provider<AuthService>{

	override fun get(): AuthService = retrofit.create(AuthService::class.java)
}