package com.graduate.spams.presentation.main.models

sealed class MainFlowAction {
	object MainFlowShowNotifyAction: MainFlowAction()
	object MainFlowBackPressedAction: MainFlowAction()
}