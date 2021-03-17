package com.univer.mvvm_coroutines_toothpick_room.presentation.detail.models

sealed class DetailEvent {
	object DetailRouteEvent: DetailEvent()
	object DetailBackPressedEvent: DetailEvent()
}