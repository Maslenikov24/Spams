package com.univer.mvvm_coroutines_toothpick_room.presentation.manage

import android.os.Bundle
import android.view.View
import com.univer.mvvm_coroutines_toothpick_room.R
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.databinding.FragmentManageBinding
import com.univer.mvvm_coroutines_toothpick_room.presentation.manage.models.ManageEvent
import toothpick.Scope
import toothpick.ktp.delegate.inject

class ManageFragment : BaseFragment<FragmentManageBinding>() {

	private val viewModel by inject<ManageViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installViewModel<ManageViewModel>()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
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

	override fun onBackPressed() {
		viewModel.obtainEvent(ManageEvent.BackPressed)
	}
}