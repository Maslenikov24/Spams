package com.univer.mvvm_coroutines_toothpick_room.presentation.search.models

sealed class SearchEvent{
	object SearchNumber: SearchEvent()
	object ConfirmExit: SearchEvent()
	object BackPressed: SearchEvent()
}
