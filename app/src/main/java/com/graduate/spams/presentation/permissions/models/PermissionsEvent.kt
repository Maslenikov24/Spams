package com.graduate.spams.presentation.permissions.models

sealed class PermissionsEvent {
	object OnBackPressed : PermissionsEvent()
	object HideSearchFloatingButton : PermissionsEvent()
}
