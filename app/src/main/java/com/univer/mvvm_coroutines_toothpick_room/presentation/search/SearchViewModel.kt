package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchViewState
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchViewModel @Inject constructor(
	private val router: Router
): BaseViewModel<SearchViewState, SearchAction, SearchEvent>() {

	private var backPressedOnce = false

	override fun obtainEvent(viewEvent: SearchEvent) {
		when (viewEvent){
			is SearchEvent.SearchNumber -> test()
			is SearchEvent.ConfirmExit -> onConfirmExit()
			is SearchEvent.BackPressed -> onBackPressed()
		}
	}

	fun test(){
		router.navigateTo(Screens.detail())
	}

	private fun onConfirmExit() {
		ui {
			viewAction = if (backPressedOnce) {
				SearchAction.BackPressed
			}
			else {
				SearchAction.ShowNotify
			}
			backPressedOnce = true
			delay(2000)
			backPressedOnce = false
		}
	}

	private fun onBackPressed() = router.exit()
}