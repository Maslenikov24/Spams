package com.graduate.spams.presentation.accept

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.model.connect.ConnectInteractor
import com.graduate.spams.presentation.accept.models.AcceptAction
import com.graduate.spams.presentation.accept.models.AcceptEvent
import com.graduate.spams.presentation.accept.models.AcceptViewState
import javax.inject.Inject

class AcceptViewModel @Inject constructor(
	private val router: Router,
	private val connectInteractor: ConnectInteractor
) : BaseViewModel<AcceptViewState, AcceptAction, AcceptEvent>() {

	override fun obtainEvent(viewEvent: AcceptEvent) {
		when (viewEvent){
			is AcceptEvent.BackPressed -> onBackPressed()
			is AcceptEvent.AcceptParent -> onAcceptParent(viewEvent.uid)
			is AcceptEvent.RejectParent -> onRejectParent()
		}
	}

	private fun onBackPressed() = router.exit()

	private fun onAcceptParent(uid: String) {
		ui {
			connectInteractor.acceptParent(uid)
		}
	}

	private fun onRejectParent() = router.exit()
}