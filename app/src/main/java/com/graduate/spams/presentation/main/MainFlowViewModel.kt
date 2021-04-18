package com.graduate.spams.presentation.main

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.main.models.MainFlowAction
import com.graduate.spams.presentation.main.models.MainFlowEvent
import com.graduate.spams.presentation.main.models.MainFlowViewState
import javax.inject.Inject

class MainFlowViewModel @Inject constructor(
	private val router: Router,
	private val mainFlowInteractor: MainFlowInteractor
): BaseViewModel<MainFlowViewState, MainFlowAction, MainFlowEvent>() {

	init {
		ui {
			mainFlowInteractor.initToken()
		}
	}

	override fun obtainEvent(viewEvent: MainFlowEvent) {
		when (viewEvent){
			is MainFlowEvent.BackPressed -> {

			}
		}
	}

	private fun openControlPanel() = router.navigateTo(Screens.manage())
}