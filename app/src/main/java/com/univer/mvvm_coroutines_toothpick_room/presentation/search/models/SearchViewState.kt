package com.univer.mvvm_coroutines_toothpick_room.presentation.search.models

import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber

sealed class SearchViewState {
	object IsLoadingHistory : SearchViewState()
	object LoadedHistory : SearchViewState()
	class ShowHistory(val data: List<PhoneNumber>): SearchViewState()
}