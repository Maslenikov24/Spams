package com.univer.mvvm_coroutines_toothpick_room.models

sealed class AppViewState{
	object AppErrorState : AppViewState()
	object AppFirstStartState: AppViewState()
	class TestState(val text: String): AppViewState()
}
