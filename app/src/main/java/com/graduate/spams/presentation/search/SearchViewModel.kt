package com.graduate.spams.presentation.search

import com.github.terrakok.cicerone.Router
import com.graduate.spams.BuildConfig
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.di.NestedRouter
import com.graduate.spams.presentation.search.models.SearchAction
import com.graduate.spams.presentation.search.models.SearchEvent
import com.graduate.spams.presentation.search.models.SearchViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

class SearchViewModel @Inject constructor(
	private val router: Router,
	@NestedRouter private val nestedRouter: Router,
	private val searchInteractor: SearchInteractor
): BaseViewModel<SearchViewState, SearchAction, SearchEvent>() {

	private var backPressedOnce = false

	override fun obtainEvent(viewEvent: SearchEvent) {
		when (viewEvent){
			is SearchEvent.SearchNumber -> searchNumber(viewEvent.number)
			is SearchEvent.LoadHistory -> getHistory()
			is SearchEvent.ConfirmExit -> onConfirmExit()
			is SearchEvent.BackPressed -> onBackPressed()
			is SearchEvent.OpenDetail -> navigateToDetail(viewEvent.number)
			is SearchEvent.OpenManage -> navigateToManage()
			is SearchEvent.DeleteFromHistory -> deleteFromHistory(viewEvent.id)
			is SearchEvent.DeleteAllHistory -> deleteAllHistory()
		}
	}

	private fun searchNumber(number: String) {
		ui {
			try {
				viewState = SearchViewState.StartLoadingNumber
				searchInteractor.searchNumber(number)
				viewState = SearchViewState.FinishedLoadingNumber
			} catch (e: Exception) {
				if (e is ConnectException || e is SocketTimeoutException) {
					if (BuildConfig.DEBUG) viewState = SearchViewState.FailedLoad(e.message)
					else viewState = SearchViewState.FailedLoad("Неполадки с интернет-соединением")
					//TODO: send short error message
				}
			}
		}
	}

	private fun getHistory(){
		ui {
			searchInteractor.getHistory().collect{
				viewState = if (it.isNotEmpty()) SearchViewState.ShowHistory(it)
				else SearchViewState.EmptyHistory
			}
		}
	}

	private fun navigateToDetail(number: String) {
		nestedRouter.navigateTo(Screens.detail(number))
		viewAction = SearchAction.Nothing
	}

	private fun navigateToManage() = router.navigateTo(Screens.manage())

	private fun deleteFromHistory(id: Long){
		ui{
			searchInteractor.deleteFromHistory(id)
		}
	}

	private fun deleteAllHistory(){
		ui{
			searchInteractor.deleteAllHistory()
		}
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