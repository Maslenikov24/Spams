package com.univer.mvvm_coroutines_toothpick_room

import com.univer.mvvm_coroutines_toothpick_room.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.models.AppEvent
import com.univer.mvvm_coroutines_toothpick_room.models.AppViewState
import javax.inject.Inject

class MainViewModel @Inject constructor(
): BaseViewModel<AppViewState, AppAction, AppEvent>() {

	override fun obtainEvent(viewEvent: AppEvent) {

	}

	fun test(){
		//viewState = AppViewState.AppErrorState
		viewState = AppViewState.TestState("Cool!")
	}


}