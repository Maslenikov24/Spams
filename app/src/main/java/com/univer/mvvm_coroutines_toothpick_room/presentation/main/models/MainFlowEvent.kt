package com.univer.mvvm_coroutines_toothpick_room.presentation.main.models

sealed class MainFlowEvent {
	object BackPressed: MainFlowEvent()
	object OpenControl: MainFlowEvent() // Control Panel
}