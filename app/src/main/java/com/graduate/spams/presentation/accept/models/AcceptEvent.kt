package com.graduate.spams.presentation.accept.models

sealed class AcceptEvent {
	object BackPressed : AcceptEvent()
	class AcceptParent(val uid: String) : AcceptEvent()
	object RejectParent : AcceptEvent()
}