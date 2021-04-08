package com.univer.mvvm_coroutines_toothpick_room.presentation.manage.models

sealed class ManageEvent {
	object BackPressed: ManageEvent()
}