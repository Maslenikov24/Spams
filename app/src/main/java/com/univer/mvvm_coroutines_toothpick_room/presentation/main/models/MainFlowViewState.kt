package com.univer.mvvm_coroutines_toothpick_room.presentation.main.models

sealed class MainFlowViewState {
	class SetToolbarWith(val title: String?): MainFlowViewState()
}