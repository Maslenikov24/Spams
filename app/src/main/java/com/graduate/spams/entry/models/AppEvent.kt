package com.graduate.spams.entry.models

sealed class AppEvent{
	object FirstStart : AppEvent()
	object BackPressed : AppEvent()
}