package com.graduate.spams.presentation.invite.models

sealed class InviteEvent{
	object BackPressed : InviteEvent()
	class InviteChild(val uid: String) : InviteEvent()
}
