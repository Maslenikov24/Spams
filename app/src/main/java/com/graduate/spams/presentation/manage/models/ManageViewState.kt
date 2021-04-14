package com.graduate.spams.presentation.manage.models

sealed class ManageViewState {
	class ShowUid(val uid: String): ManageViewState()
}