package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models

sealed class RecentAction {
	object BackPressed : RecentAction()
	object ShowNotify : RecentAction()
}