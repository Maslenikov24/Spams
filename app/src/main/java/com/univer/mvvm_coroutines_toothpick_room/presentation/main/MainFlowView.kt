package com.univer.mvvm_coroutines_toothpick_room.presentation.main

import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowViewState

interface MainFlowView {
	fun renderViewState(mainFlowViewState: MainFlowViewState)
	fun renderActions(mainFlowActions: MainFlowAction)
}