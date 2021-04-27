package com.graduate.spams.model.preferences.app

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.graduate.spams.core.extensions.tryCollectAsState
import com.graduate.spams.model.preferences.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferenceStorageImpl @Inject constructor(
	private val context: Context
): AppPreferenceStorage {

	private val Context._dataStore by preferencesDataStore("app_preferences")

	private val dataStore by lazy { context._dataStore}

	override val isFirstStart: Flow<Boolean>
		get() = dataStore.data
			.map { preferences ->
				preferences[PreferencesKeys.App.IS_FIRST_START] ?: true
			}

	override val uiMode: Flow<UiMode>
		get() = dataStore.data
			.map { preferences ->
				when (preferences[PreferencesKeys.App.IS_DARK_MODE] ?: false) {
					true -> UiMode.NIGHT
					false -> UiMode.DAY
				}
			}

	override val registrationToken: Flow<String>
		get() = dataStore.data
			.map { preferences ->
				preferences[PreferencesKeys.App.REGISTRATION_TOKEN] ?: ""
			}

	override val sessionToken: String?
		get() = dataStore.data
			.map { preferences ->
				preferences[PreferencesKeys.App.SESSION_TOKEN]
			}.tryCollectAsState(null)

	override val uid: Flow<String>
		get() = dataStore.data
			.map { preferences ->
				preferences[PreferencesKeys.App.UID] ?: ""
			}

	override suspend fun<T> setValue(key: Preferences.Key<T>, value: T) = dataStore
		.edit {
			it[key] = value
		}

	override suspend fun clearPreferenceStorage() {
		dataStore.edit {
			it.clear()
		}
	}
}