package com.univer.mvvm_coroutines_toothpick_room.presentation.search.models

sealed class SearchEvent{
	class SearchNumber(val number: String): SearchEvent()
	object LoadHistory: SearchEvent()
	object ConfirmExit: SearchEvent()
	object BackPressed: SearchEvent()
	class OpenDetail(val number: String): SearchEvent()
	class DeleteFromHistory(val id: Long): SearchEvent()
	object DeleteAllHistory : SearchEvent()
}
