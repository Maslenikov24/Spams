package com.graduate.spams.presentation.detail

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.detail.models.DetailAction
import com.graduate.spams.presentation.detail.models.DetailEvent
import com.graduate.spams.presentation.detail.models.DetailViewState
import javax.inject.Inject

class DetailViewModel @Inject constructor(
	private val initParams: InitParams,
	private val router: Router,
	private val detailInteractor: DetailInteractor
) : BaseViewModel<DetailViewState, DetailAction, DetailEvent>() {

	data class InitParams(
		val phoneNumber: String?
	)

	private val phoneNumber = initParams.phoneNumber

	init {
		ui{
			phoneNumber?.let {
				viewState = DetailViewState.StartLoadingNumber
				val result = detailInteractor.searchNumber(phoneNumber)
				viewState = DetailViewState.ShowDetail(result!!) //Todo: fixed it
				viewState = DetailViewState.FinishedLoadingNumber
			}
		}
	}

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