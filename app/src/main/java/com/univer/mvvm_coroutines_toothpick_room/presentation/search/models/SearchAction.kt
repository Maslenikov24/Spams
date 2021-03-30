package com.univer.mvvm_coroutines_toothpick_room.presentation.search.models

sealed class SearchAction {
	object BackPressed : SearchAction()
	object ShowNotify : SearchAction()
	object Nothing : SearchAction()
}