package com.graduate.spams.presentation.recent.models

import com.graduate.spams.data.contact.domain.Contact

sealed class RecentViewState {
	object IsLoadingRecent : RecentViewState()
	object LoadedRecent : RecentViewState()
	class ShowRecent(val data: List<Contact>) : RecentViewState()
}