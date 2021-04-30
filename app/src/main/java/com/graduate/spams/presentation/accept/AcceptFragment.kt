package com.graduate.spams.presentation.accept

import android.os.Bundle
import android.view.View
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentAcceptBinding
import com.graduate.spams.model.connect.ConnectInteractor
import com.graduate.spams.model.connect.ConnectInteractorImpl
import com.graduate.spams.presentation.accept.models.AcceptEvent
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class AcceptFragment : BaseFragment<FragmentAcceptBinding>() {

	private val viewModel by inject<AcceptViewModel>()

	private val uid get() = arguments?.getString(ARG_UID) ?: ""

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules( module {
			bind<ConnectInteractor>().toClass<ConnectInteractorImpl>()
		})
		scope.installViewModel<AcceptViewModel>()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binging) {
			accept.setOnClickListener {
				viewModel.obtainEvent(AcceptEvent.AcceptParent(uid))
			}
			reject.setOnClickListener {
				viewModel.obtainEvent(AcceptEvent.RejectParent)
			}
			parentUid.text = uid
		}
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(AcceptEvent.BackPressed)
	}

	companion object {
		const val ARG_UID = "arg_uid"

		fun newInstance(acceptStartParams: AcceptStartParams) = AcceptFragment().apply {
			arguments = Bundle().apply {
				putString(ARG_UID, acceptStartParams.uid)
			}
		}

	}
}