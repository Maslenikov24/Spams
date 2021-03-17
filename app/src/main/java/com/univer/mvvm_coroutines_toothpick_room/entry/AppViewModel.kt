package com.univer.mvvm_coroutines_toothpick_room.entry

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppEvent
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppViewState
import javax.inject.Inject

class AppViewModel @Inject constructor(
	private val router: Router,
	navigatorHolder: NavigatorHolder
): BaseViewModel<AppViewState, AppAction, AppEvent>(navigatorHolder) {

	override fun obtainEvent(viewEvent: AppEvent) {
		when (viewEvent){
			is AppEvent.AppFirstStartEvent -> firstLaunch()
			is AppEvent.AppBackPressedEvent -> onBackPressed()
		}
	}

	private fun firstLaunch() = router.newRootScreen(Screens.main())

	private fun initialized(){
		viewState = AppViewState.TestState("Cool!")
		viewAction = AppAction.AppLogAction
	}

	private fun onBackPressed(){
		/* nothing */
	}
}