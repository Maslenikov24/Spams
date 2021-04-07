package com.univer.mvvm_coroutines_toothpick_room.model.preferences.call

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.collectAsState
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CallPreferenceStorageImpl @Inject constructor(
	private val context: Context
): CallPreferenceStorage {

	private val Context._dataStore by preferencesDataStore("call_preferences")

	private val dataStore by lazy { context._dataStore}

	fun getValue(key: Preferences.Key<Boolean>, defaultValue: Boolean) = dataStore.data
		.map {
			it[key] ?: false
		}.collectAsState(defaultValue)

	override suspend fun setValue(key: Preferences.Key<Boolean>, value: Boolean) = dataStore
		.edit {
			it[key] = value
		}

	override suspend fun clearPreferenceStorage() {
		dataStore.edit {
			it.clear()
		}
	}

	override val isNeedBlock: Boolean
		get() = getValue(PreferencesKeys.Call.IS_NEED_BLOCK, false)
	override val shouldDisallowCall: Boolean
		get() = getValue(PreferencesKeys.Call.SHOULD_DISALLOW_CALL, false)
	override val shouldRejectCall: Boolean
		get() = getValue(PreferencesKeys.Call.SHOULD_REJECT_CALL, false)
	override val shouldSkipCallLog: Boolean
		get() = getValue(PreferencesKeys.Call.SHOULD_SKIP_CALL_LOG, false)
	override val shouldSilenceCall: Boolean
		get() = getValue(PreferencesKeys.Call.SHOULD_SILENCE_CALL, false)
	override val shouldSkipNotification: Boolean
		get() = getValue(PreferencesKeys.Call.SHOULD_SKIP_NOTIFICATION, false)
}