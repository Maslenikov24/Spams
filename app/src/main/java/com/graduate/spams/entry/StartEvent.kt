package com.graduate.spams.entry

sealed class StartEvent{
	object Empty : StartEvent()
	class AcceptParent(val uid: String) : StartEvent()
}
