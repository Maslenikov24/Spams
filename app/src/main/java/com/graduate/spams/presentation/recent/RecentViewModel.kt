package com.graduate.spams.presentation.recent

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.di.NestedRouter
import com.graduate.spams.presentation.detail.DetailStartParams
import com.graduate.spams.presentation.recent.models.RecentAction
import com.graduate.spams.presentation.recent.models.RecentEvent
import com.graduate.spams.presentation.recent.models.RecentViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class RecentViewModel @Inject constructor(
	private val router : Router,
	@NestedRouter private val nestedRouter : Router,
	private val recentInteractor: RecentInteractor
): BaseViewModel<RecentViewState, RecentAction, RecentEvent>() {

	private var backPressedOnce = false

	override fun obtainEvent(viewEvent: RecentEvent) {
		when (viewEvent) {
			is RecentEvent.ConfirmExit -> onConfirmExit()
			is RecentEvent.BackPressed -> onBackPressed()
			is RecentEvent.LoadRecent -> getRecent()
			is RecentEvent.OpenDetail -> navigateToDetail(viewEvent.number, viewEvent.name)
			is RecentEvent.OpenManage -> navigateToManage()
		}
	}

	private fun navigateToDetail(number: String, name: String?) =
		nestedRouter.navigateTo(Screens.detail(DetailStartParams(number, name)))

	private fun navigateToManage() = router.navigateTo(Screens.manage())

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