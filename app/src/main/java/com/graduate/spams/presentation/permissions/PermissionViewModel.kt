package com.graduate.spams.presentation.permissions

import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.PermissionsListener
import com.graduate.spams.core.presentation.BaseViewModel
import com.graduate.spams.presentation.permissions.models.PermissionsAction
import com.graduate.spams.presentation.permissions.models.PermissionsEvent
import com.graduate.spams.presentation.permissions.models.PermissionsViewState
import javax.inject.Inject

class PermissionViewModel @Inject constructor(
	private val router: Router,
	private val permissionsListener: PermissionsListener
) : BaseViewModel<PermissionsViewState, PermissionsAction, PermissionsEvent>() {

	override fun obtainEvent(viewEvent: PermissionsEvent) {
		when (viewEvent){
			is PermissionsEvent.OnBackPressed -> onBackPressed()
			is PermissionsEvent.HideSearchFloatingButton -> hideSearchFloatingButton()
		}
	}

	private fun hideSearchFloatingButton(){
		permissionsListener.sendMessage(false)
	}

	private fun onBackPressed() = router.exit()
}