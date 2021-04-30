package com.graduate.spams.presentation.invite

import android.os.Bundle
import android.view.View
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentInviteBinding
import com.graduate.spams.model.connect.ConnectInteractor
import com.graduate.spams.model.connect.ConnectInteractorImpl
import com.graduate.spams.presentation.invite.models.InviteEvent
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class InviteFragment : BaseFragment<FragmentInviteBinding>() {

	private val viewModel by inject<InviteViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules( module {
			bind<ConnectInteractor>().toClass<ConnectInteractorImpl>()
		})
		scope.installViewModel<InviteViewModel>()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(binging){
			inviteButton.setOnClickListener {
				viewModel.obtainEvent(InviteEvent.InviteChild(childInput.text.toString()))
			}
			toolbar.setNavigationOnClickListener {
				onBackPressed()
			}
		}
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(InviteEvent.BackPressed)
	}

}