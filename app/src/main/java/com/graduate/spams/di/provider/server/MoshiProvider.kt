package com.graduate.spams.di.provider.server

import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Provider

//TODO: understand it
class MoshiProvider @Inject internal constructor(): Provider<MoshiConverterFactory>{

	override fun get() = MoshiConverterFactory.create()

}