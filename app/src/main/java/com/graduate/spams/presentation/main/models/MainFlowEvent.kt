package com.graduate.spams.presentation.main.models

sealed class MainFlowEvent {
	object BackPressed: MainFlowEvent()
	object OpenControl: MainFlowEvent() // Control Panel
}