package com.univer.mvvm_coroutines_toothpick_room.model.preferences.call

import androidx.datastore.preferences.core.Preferences

interface CallPreferenceStorage {

	val isNeedBlock: Boolean
	val shouldDisallowCall: Boolean
	val shouldRejectCall: Boolean
	val shouldSkipCallLog: Boolean
	val shouldSilenceCall: Boolean
	val shouldSkipNotification: Boolean
	suspend fun setValue(key: Preferences.Key<Boolean>, value: Boolean): Preferences
	suspend fun clearPreferenceStorage()
}