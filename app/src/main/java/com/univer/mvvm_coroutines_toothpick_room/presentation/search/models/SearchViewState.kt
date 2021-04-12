package com.univer.mvvm_coroutines_toothpick_room.presentation.search.models

import com.univer.mvvm_coroutines_toothpick_room.data.history.domain.HistoryNumber

sealed class SearchViewState {
	object StartLoadingNumber : SearchViewState()
	object FinishedLoadingNumber : SearchViewState()
	class FailedLoad(val message: String?) : SearchViewState()
	class ShowHistory(val data: List<HistoryNumber>): SearchViewState()
}