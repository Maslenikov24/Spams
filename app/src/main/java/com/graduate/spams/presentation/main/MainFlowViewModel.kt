package com.graduate.spams.presentation.main

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.main.models.MainFlowAction
import com.graduate.spams.presentation.main.models.MainFlowEvent
import com.graduate.spams.presentation.main.models.MainFlowViewState
import javax.inject.Inject

class MainFlowViewModel @Inject constructor(
	private val router: Router
): BaseViewModel<MainFlowViewState, MainFlowAction, MainFlowEvent>() {

	override fun obtainEvent(viewEvent: MainFlowEvent) {
		when (viewEvent){
			is MainFlowEvent.BackPressed -> {

			}
			is MainFlowEvent.OpenControl -> {
				openControlPanel()
			}
		}
	}

	private fun openControlPanel() = router.navigateTo(Screens.manage())
}