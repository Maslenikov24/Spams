package com.graduate.spams.presentation.manage

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.manage.models.ManageAction
import com.graduate.spams.presentation.manage.models.ManageEvent
import com.graduate.spams.presentation.manage.models.ManageViewState
import javax.inject.Inject

class ManageViewModel @Inject constructor(
	private val router: Router
): BaseViewModel<ManageViewState, ManageAction, ManageEvent>() {


	override fun obtainEvent(viewEvent: ManageEvent) {
		when (viewEvent){
			is ManageEvent.BackPressed -> onBackPressed()
		}
	}

	fun onBackPressed() = router.exit()
}