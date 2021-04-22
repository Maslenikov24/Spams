package com.graduate.spams.presentation.manage.models

sealed class ManageEvent {
	object BackPressed : ManageEvent()
	object OpenSettings : ManageEvent()
	object OpenPermissions : ManageEvent()
	object OpenAbout : ManageEvent()
}