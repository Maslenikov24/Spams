package com.graduate.spams.presentation.main

import com.graduate.spams.presentation.main.models.MainFlowAction
import com.graduate.spams.presentation.main.models.MainFlowViewState

interface MainFlowView {
	fun renderViewState(viewState: MainFlowViewState)
	fun renderActions(viewActions: MainFlowAction)
}