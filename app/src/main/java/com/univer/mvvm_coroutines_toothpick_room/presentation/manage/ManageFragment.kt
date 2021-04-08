package com.univer.mvvm_coroutines_toothpick_room.presentation.manage

import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.databinding.FragmentManageBinding
import com.univer.mvvm_coroutines_toothpick_room.presentation.manage.models.ManageEvent
import toothpick.Scope
import toothpick.ktp.delegate.inject

class ManageFragment : BaseFragment<FragmentManageBinding>() {

	private val viewModel by inject<ManageViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(ManageEvent.BackPressed)
	}
}