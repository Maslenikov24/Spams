package com.univer.mvvm_coroutines_toothpick_room.entry

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.PreferencesKeys
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.app.AppPreferenceStorage
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppEvent
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import javax.inject.Inject

class AppViewModel @Inject constructor(
	private val router: Router,
	private val dataStore: AppPreferenceStorage,
	navigatorHolder: NavigatorHolder // Todo: private?
): BaseViewModel<AppViewState, AppAction, AppEvent>(navigatorHolder) {

	override fun obtainEvent(viewEvent: AppEvent) {
		when (viewEvent){
			is AppEvent.FirstStart -> firstLaunch()
			is AppEvent.BackPressed -> onBackPressed()
		}
	}

	private fun firstLaunch() {
		ui {
			dataStore.uiMode.collect {
				viewState = AppViewState.SetUiMode(it)
			}
		}
		router.newRootScreen(Screens.main())
	}

	private fun onBackPressed(){
		/* nothing */
	}
}