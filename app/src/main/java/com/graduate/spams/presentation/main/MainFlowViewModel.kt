package com.graduate.spams.presentation.main

import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.main.models.MainFlowAction
import com.graduate.spams.presentation.main.models.MainFlowEvent
import com.graduate.spams.presentation.main.models.MainFlowViewState
import javax.inject.Inject

class MainFlowViewModel @Inject constructor(
	private val mainFlowInteractor: MainFlowInteractor,
): BaseViewModel<MainFlowViewState, MainFlowAction, MainFlowEvent>() {

	init {
		ui {
			mainFlowInteractor.apply {
				initToken()
				login()
			}
		}
	}

	override fun obtainEvent(viewEvent: MainFlowEvent) {
		when (viewEvent){
			is MainFlowEvent.BackPressed -> {

			}
		}
	}
}