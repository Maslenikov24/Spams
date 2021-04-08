package com.univer.mvvm_coroutines_toothpick_room.presentation.main

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowViewState
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