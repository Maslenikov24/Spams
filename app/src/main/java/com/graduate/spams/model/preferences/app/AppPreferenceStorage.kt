package com.graduate.spams.model.preferences.app

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AppPreferenceStorage {
	val uiMode: Flow<UiMode>
	suspend fun setValue(key: Preferences.Key<Boolean>, value: Boolean): Preferences
	suspend fun clearPreferenceStorage()
}