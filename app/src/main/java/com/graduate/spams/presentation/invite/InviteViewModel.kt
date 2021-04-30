package com.graduate.spams.presentation.invite

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.model.connect.ConnectInteractor
import com.graduate.spams.presentation.invite.models.InviteAction
import com.graduate.spams.presentation.invite.models.InviteEvent
import com.graduate.spams.presentation.invite.models.InviteViewState
import javax.inject.Inject

class InviteViewModel @Inject constructor(
	private val router: Router,
	private val connectInteractor: ConnectInteractor
): BaseViewModel<InviteViewState, InviteAction, InviteEvent>() {

	override fun obtainEvent(viewEvent: InviteEvent) {
		when (viewEvent){
			is InviteEvent.BackPressed -> onBackPressed()
			is InviteEvent.InviteChild -> inviteChild(viewEvent.uid)
		}
	}

	fun inviteChild(uid: String) {
		ui{
			connectInteractor.inviteChild(uid)
		}
	}

	fun onBackPressed() = router.exit()
}