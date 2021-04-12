package com.graduate.spams.model.preferences.app

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.graduate.spams.model.preferences.PreferencesKeys
import com.graduate.spams.model.preferences.app.UiMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferenceStorageImpl @Inject constructor(
	private val context: Context
): AppPreferenceStorage {

	private val Context._dataStore by preferencesDataStore("app_preferences")

	private val dataStore by lazy { context._dataStore}

	override val uiMode: Flow<UiMode>
		get() = context._dataStore.data
			.map { preferences ->
				when (preferences[PreferencesKeys.App.IS_DARK_MODE] ?: false) {
					true -> UiMode.NIGHT
					false -> UiMode.DAY
				}
			}

	override suspend fun setValue(key: Preferences.Key<Boolean>, value: Boolean) = dataStore
		.edit {
			it[key] = value
		}

	override suspend fun clearPreferenceStorage() {
		dataStore.edit {
			it.clear()
		}
	}
}