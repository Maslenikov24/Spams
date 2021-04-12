package com.graduate.spams.di.provider.server

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject
import javax.inject.Provider

class DataStoreProvider @Inject constructor(
	private val context: Context
) : Provider<DataStore<*>> {

	private val Context.dataStore by preferencesDataStore("app_preferences")

	override fun get() = context.dataStore
}