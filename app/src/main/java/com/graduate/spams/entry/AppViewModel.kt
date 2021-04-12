package com.graduate.spams.entry

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.graduate.spams.model.preferences.PreferencesKeys
import com.graduate.spams.model.preferences.app.AppPreferenceStorage
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.entry.models.AppAction
import com.graduate.spams.entry.models.AppEvent
import com.graduate.spams.entry.models.AppViewState
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