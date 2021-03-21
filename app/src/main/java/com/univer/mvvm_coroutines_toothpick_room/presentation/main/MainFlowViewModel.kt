package com.univer.mvvm_coroutines_toothpick_room.presentation.main

import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowViewState
import javax.inject.Inject

class MainFlowViewModel @Inject constructor(
): BaseViewModel<MainFlowViewState, MainFlowAction, MainFlowEvent>() {

	var backPressedOnce = false

	override fun obtainEvent(viewEvent: MainFlowEvent) {
		when (viewEvent){
			is MainFlowEvent.MainFlowBackPressedEvent -> {

			}
		}
	}
}