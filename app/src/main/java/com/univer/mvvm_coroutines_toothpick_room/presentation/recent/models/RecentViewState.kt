package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models

import com.univer.mvvm_coroutines_toothpick_room.data.contact.domain.Contact

sealed class RecentViewState {
	object IsLoadingRecent : RecentViewState()
	object LoadedRecent : RecentViewState()
	class ShowRecent(val data: List<Contact>) : RecentViewState()
}