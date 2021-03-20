package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.domain.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchViewModel @Inject constructor(
	private val router: Router
): BaseViewModel<SearchViewState, SearchAction, SearchEvent>() {

	private var backPressedOnce = false

	override fun obtainEvent(viewEvent: SearchEvent) {
		when (viewEvent){
			is SearchEvent.SearchNumber -> test()
			is SearchEvent.LoadHistory -> getHistory()
			is SearchEvent.ConfirmExit -> onConfirmExit()
			is SearchEvent.BackPressed -> onBackPressed()
		}
	}

	private fun test(){
		router.navigateTo(Screens.detail())
	}

//	like setHistory
	private fun getHistory() {

		val data = mutableListOf<PhoneNumber>()
		for (item in 1..15){
			data.add(PhoneNumber("", "${item*11111111}", 0))
		}
		showData(data = data.toList())
	}

	private fun showData(data: List<PhoneNumber>){
		viewState = SearchViewState.ShowHistory(data)
	}

	//val data = mutableListOf<PhoneNumber>()

	/*private fun getHistory(){
		io {
			setHistory().collect { item ->
				data.add(0, item)
				viewState = SearchViewState.LoadedHistory(data)
			}
		}
	}

	private fun setHistory() = flow {
		for (item in 15 downTo 1){
			delay(100)
			emit(PhoneNumber("", "${item*11111111}", 0))
		}
	}*/


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