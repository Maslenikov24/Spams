package com.univer.mvvm_coroutines_toothpick_room.model.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKeys {

	object App {
		val IS_DARK_MODE = booleanPreferencesKey("dark_mode")
	}

	object Call {
		val IS_NEED_BLOCK = booleanPreferencesKey("need_block")
		val SHOULD_DISALLOW_CALL = booleanPreferencesKey("disallow_call")
		val SHOULD_REJECT_CALL = booleanPreferencesKey("reject_call")
		val SHOULD_SKIP_CALL_LOG = booleanPreferencesKey("skip_call_log")
		val SHOULD_SKIP_NOTIFICATION = booleanPreferencesKey("skip_notification")
		val SHOULD_SILENCE_CALL = booleanPreferencesKey("silence_call")
	}
}