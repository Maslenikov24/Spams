package com.graduate.spams.di.provider.server

import javax.inject.Inject
import javax.inject.Provider

class ApiPathProvider @Inject constructor(
) : Provider<String> {

	override fun get(): String = "http://25.92.111.229:3000/api/"
}
