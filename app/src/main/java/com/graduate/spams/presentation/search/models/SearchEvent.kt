package com.graduate.spams.presentation.search.models

sealed class SearchEvent {
	class SearchNumber(val number: String) : SearchEvent()
	object LoadHistory : SearchEvent()
	object ConfirmExit : SearchEvent()
	object BackPressed : SearchEvent()
	class OpenDetail(val number: String) : SearchEvent()
	object OpenManage : SearchEvent()
	object OpenPermissions : SearchEvent()
	class DeleteFromHistory(val id: Long) : SearchEvent()
	object DeleteAllHistory : SearchEvent()
}
