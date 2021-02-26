package com.univer.mvvm_coroutines_toothpick_room.models

sealed class AppEvent{
	object AppFirstStartEvent : AppEvent()
}