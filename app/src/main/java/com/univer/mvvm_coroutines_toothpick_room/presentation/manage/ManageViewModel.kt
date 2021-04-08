package com.univer.mvvm_coroutines_toothpick_room.presentation.manage

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.manage.models.ManageAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.manage.models.ManageEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.manage.models.ManageViewState
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