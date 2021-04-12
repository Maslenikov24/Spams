package com.graduate.spams.presentation.main.models

sealed class MainFlowViewState {
	class SetToolbarWith(val title: String?): MainFlowViewState()
}