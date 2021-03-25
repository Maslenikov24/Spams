package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models

sealed class RecentEvent {
	object ConfirmExit: RecentEvent()
	object BackPressed: RecentEvent()
	object LoadingRecent: RecentEvent()
	object LoadRecent: RecentEvent()
	object EmptyData: RecentEvent()
	class OpenDetail(val number: String, val name: String?): RecentEvent()
}