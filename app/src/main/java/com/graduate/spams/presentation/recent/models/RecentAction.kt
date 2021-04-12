package com.graduate.spams.presentation.recent.models

sealed class RecentAction {
	object BackPressed : RecentAction()
	object ShowNotify : RecentAction()
}