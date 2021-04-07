package com.univer.mvvm_coroutines_toothpick_room.entry.models

sealed class AppEvent{
	object FirstStart : AppEvent()
	object BackPressed : AppEvent()
}