package com.graduate.spams.model.preferences.app

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AppPreferenceStorage {
	val isFirstStart: Flow<Boolean>
	val uiMode: Flow<UiMode>
	val registrationToken: Flow<String>
	val sessionToken: String?
	val uid: Flow<String>
	suspend fun<T> setValue(key: Preferences.Key<T>, value: T): Preferences
	suspend fun clearPreferenceStorage()
}