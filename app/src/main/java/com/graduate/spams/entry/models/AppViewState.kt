package com.graduate.spams.entry.models

import com.graduate.spams.model.preferences.app.UiMode

sealed class AppViewState{
	object FirstStart: AppViewState()
	class SetUiMode(val uiMode: UiMode): AppViewState()
}
