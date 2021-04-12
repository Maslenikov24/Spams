package com.graduate.spams.presentation.detail

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.Screens
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.detail.models.DetailAction
import com.graduate.spams.presentation.detail.models.DetailEvent
import com.graduate.spams.presentation.detail.models.DetailViewState
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