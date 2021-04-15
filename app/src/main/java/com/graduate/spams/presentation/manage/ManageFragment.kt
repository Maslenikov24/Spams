package com.graduate.spams.presentation.manage

import android.os.Bundle
import android.view.View
import com.graduate.spams.R
import com.graduate.spams.core.extensions.subscribe
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentManageBinding
import com.graduate.spams.presentation.manage.models.ManageEvent
import com.graduate.spams.presentation.manage.models.ManageViewState
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class ManageFragment : BaseFragment<FragmentManageBinding>() {

	private val viewModel by inject<ManageViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules( module {
			bind<ManageInteractor>().toClass<ManageInteractorImpl>()
		})
		scope.installViewModel<ManageViewModel>()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		subscribe(viewModel.viewStates(), ::renderViewState)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binging.toolbar.apply {
			setNavigationIcon(R.drawable.ic_back)
			setNavigationOnClickListener {
				viewModel.obtainEvent(ManageEvent.BackPressed)
			}
		}
	}

	fun renderViewState(viewState: ManageViewState){
		when (viewState){
			is ManageViewState.ShowUid -> {
				binging.uidNumber.text = viewState.uid
			}
		}
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(ManageEvent.BackPressed)
	}
}