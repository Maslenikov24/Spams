package com.graduate.spams.presentation.detail.models

sealed class DetailEvent {
	object DetailRouteEvent: DetailEvent()
	object DetailBackPressedEvent: DetailEvent()
}