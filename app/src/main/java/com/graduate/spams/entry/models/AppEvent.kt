package com.graduate.spams.entry.models

import com.graduate.spams.entry.StartEvent

sealed class AppEvent{
	object FirstStart : AppEvent()
	object BackPressed : AppEvent()
	class HandleStartEvent (val startEvent: StartEvent, val isOpenInside: Boolean): AppEvent()
}