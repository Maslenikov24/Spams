package com.univer.mvvm_coroutines_toothpick_room.presentation.recent

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models.RecentAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models.RecentEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models.RecentViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class RecentViewModel @Inject constructor(
	private val router : Router,
	private val recentInteractor: RecentInteractor
): BaseViewModel<RecentViewState, RecentAction, RecentEvent>() {

	private var backPressedOnce = false

	override fun obtainEvent(viewEvent: RecentEvent) {
		when (viewEvent) {
			is RecentEvent.ConfirmExit -> onConfirmExit()
			is RecentEvent.BackPressed -> onBackPressed()
			is RecentEvent.LoadRecent -> getRecent()
			is RecentEvent.OpenDetail -> navigateToDetail(viewEvent.number, viewEvent.name)
		}
	}

	private fun navigateToDetail(number: String, name: String?) =
		router.navigateTo(Screens.detail(number, name))

	private fun getRecent() {
		ui {
			recentInteractor.getPhoneBook().collect {
				viewState = RecentViewState.ShowRecent(it)
			}
		}
	}

	private fun onBackPressed() = router.exit()

	private fun onConfirmExit() {
		ui {
			viewAction = if (backPressedOnce) {
				RecentAction.BackPressed
			} else {
				RecentAction.ShowNotify
			}
			backPressedOnce = true
			delay(2000)
			backPressedOnce = false
		}
	}
}