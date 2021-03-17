package com.univer.mvvm_coroutines_toothpick_room.presentation.main.models

sealed class MainFlowAction {
	object MainFlowShowNotifyAction: MainFlowAction()
	object MainFlowBackPressedAction: MainFlowAction()
}