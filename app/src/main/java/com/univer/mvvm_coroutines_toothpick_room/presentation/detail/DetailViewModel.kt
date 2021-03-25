package com.univer.mvvm_coroutines_toothpick_room.presentation.detail

import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Screens
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseViewModel
import com.univer.mvvm_coroutines_toothpick_room.presentation.detail.models.DetailAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.detail.models.DetailEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.detail.models.DetailViewState
import javax.inject.Inject

class DetailViewModel @Inject constructor(
	private val router: Router
) : BaseViewModel<DetailViewState, DetailAction, DetailEvent>() {

	override fun obtainEvent(viewEvent: DetailEvent) {
		when (viewEvent) {
			is DetailEvent.DetailRouteEvent -> {

			}
			is DetailEvent.DetailBackPressedEvent -> {
				onBackPressed()
			}
		}
	}

	private fun onBackPressed() = router.exit()
}