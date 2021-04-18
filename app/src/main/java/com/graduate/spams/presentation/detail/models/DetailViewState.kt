package com.graduate.spams.presentation.detail.models
import com.graduate.spams.data.number.domain.PhoneNumber

sealed class DetailViewState {
	object StartLoadingNumber : DetailViewState()
	object FinishedLoadingNumber : DetailViewState()
	class FailedLoad(val message: String?) : DetailViewState()
	class ShowDetail(val phoneNumber: PhoneNumber) : DetailViewState()
}