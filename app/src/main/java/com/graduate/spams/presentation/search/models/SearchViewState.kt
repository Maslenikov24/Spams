package com.graduate.spams.presentation.search.models

import com.graduate.spams.data.history.domain.HistoryNumber

sealed class SearchViewState {
	object StartLoadingNumber : SearchViewState()
	object FinishedLoadingNumber : SearchViewState()
	class FailedLoad(val message: String?) : SearchViewState()
	class ShowHistory(val data: List<HistoryNumber>): SearchViewState()
}