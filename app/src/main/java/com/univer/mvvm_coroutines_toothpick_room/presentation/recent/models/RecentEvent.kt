package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models

sealed class RecentEvent {
	object ConfirmExit: RecentEvent()
	object BackPressed: RecentEvent()
	object LoadRecent: RecentEvent()
}