package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchViewState
import javax.inject.Inject

class SearchViewModel @Inject constructor(
	private val router: Router
): BaseViewModel<SearchViewState, SearchAction, SearchEvent>() {

	override fun obtainEvent(viewEvent: SearchEvent) {
		when (viewEvent){
			is SearchEvent.SearchNumberEvent -> test()
		}
	}

	fun test(){
		router.navigateTo(Screens.recent())
	}
}