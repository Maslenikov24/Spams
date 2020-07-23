package com.univer.mvvm_coroutines_toothpick_room

import javax.inject.Inject

class MainViewModel @Inject constructor(

): BaseViewModel<AppState>() {

	override val defaultState: AppState
		get() = AppState(isError = false, testString = "zero")


	fun testAction() {
		action.value = currentState.copy(testString = "Success")
	}
}