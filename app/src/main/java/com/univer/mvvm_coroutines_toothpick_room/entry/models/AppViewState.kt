package com.univer.mvvm_coroutines_toothpick_room.entry.models

import com.univer.mvvm_coroutines_toothpick_room.model.preferences.app.UiMode

sealed class AppViewState{
	object FirstStart: AppViewState()
	class SetUiMode(val uiMode: UiMode): AppViewState()
}
