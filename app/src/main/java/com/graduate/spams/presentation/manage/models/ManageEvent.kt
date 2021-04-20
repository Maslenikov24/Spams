package com.graduate.spams.presentation.manage.models

sealed class ManageEvent {
	object BackPressed: ManageEvent()
	object OpenAbout: ManageEvent()
	object OpenSettings: ManageEvent()
}