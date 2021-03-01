package com.univer.mvvm_coroutines_toothpick_room.entry

import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppEvent
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppViewState
import javax.inject.Inject

class AppViewModel @Inject constructor(
): BaseViewModel<AppViewState, AppAction, AppEvent>() {

	override fun obtainEvent(viewEvent: AppEvent) {
		when (viewEvent){
			is AppEvent.AppFirstStartEvent -> {
				initialized()
			}
		}
	}

	private fun initialized(){
		viewState = AppViewState.TestState("Cool!")
		viewAction = AppAction.AppLogAction
	}


}