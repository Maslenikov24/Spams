package com.univer.mvvm_coroutines_toothpick_room.model.preferences.app

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AppPreferenceStorage {
	val uiMode: Flow<UiMode>
	suspend fun setValue(key: Preferences.Key<Boolean>, value: Boolean): Preferences
	suspend fun clearPreferenceStorage()
}