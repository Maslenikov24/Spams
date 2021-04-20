package com.graduate.spams.presentation.manage

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.manage.models.ManageAction
import com.graduate.spams.presentation.manage.models.ManageEvent
import com.graduate.spams.presentation.manage.models.ManageViewState
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ManageViewModel @Inject constructor(
	private val router: Router,
	private val manageInteractor: ManageInteractor
): BaseViewModel<ManageViewState, ManageAction, ManageEvent>() {

	init {
		getUid()
	}

	override fun obtainEvent(viewEvent: ManageEvent) {
		when (viewEvent){
			is ManageEvent.BackPressed -> onBackPressed()
			is ManageEvent.OpenAbout -> openAbout()
			is ManageEvent.OpenSettings -> {}
		}
	}

	private fun getUid(){
		ui{
			manageInteractor.getUid().collect { uid ->
				viewState = ManageViewState.ShowUid(uid)
			}
		}
	}

	private fun openAbout() = router.navigateTo(Screens.about())

	fun onBackPressed() = router.exit()
}