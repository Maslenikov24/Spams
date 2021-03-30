package com.univer.mvvm_coroutines_toothpick_room.presentation.recent

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.Permissions
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.checkPermission
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.snack
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.subscribe
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.data.contact.domain.Contact
import com.univer.mvvm_coroutines_toothpick_room.databinding.FragmentRecentBinding
import com.univer.mvvm_coroutines_toothpick_room.presentation.common.RouterProvider
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.adapter.RecentAdapter
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models.RecentAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models.RecentEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.models.RecentViewState
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class RecentFragment: BaseFragment<FragmentRecentBinding>() {

	private val viewModel by inject<RecentViewModel>()
	private val adapter by inject<RecentAdapter>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules(module {
			bind<Router>().toInstance((parentFragment as RouterProvider).router)
			bind<RecentInteractor>().toClass<RecentInteractorImpl>()
			bind<(String, String?) -> Unit>().toInstance { number, name ->
				viewModel.obtainEvent(RecentEvent.OpenDetail(number, name))
			}
		})
		scope.installViewModel<RecentViewModel>()
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		var accessCallLog = true
		permissions.forEachIndexed { index, it ->
			when (it){
				Permissions.READ_CALL_LOG -> {
					if (grantResults[index] != 0) accessCallLog = false
				}
			}
		}
		if (accessCallLog){
			viewModel.obtainEvent(RecentEvent.LoadRecent)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		subscribe(viewModel.viewStates(), ::renderViewState)
		subscribe(viewModel.viewActions(), ::renderAction)

		binging.recyclerView.adapter = adapter

		if (this.checkPermission(requireContext())) viewModel.obtainEvent(RecentEvent.LoadRecent)
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(RecentEvent.ConfirmExit)
	}

	private fun renderViewState(viewState: RecentViewState){
		when (viewState){
			is RecentViewState.ShowRecent -> {
				showData(viewState.data)
			}
		}
	}

	private fun renderAction(viewAction: RecentAction){
		when (viewAction){
			is RecentAction.ShowNotify -> {
				view?.snack("Confirm exit?")
			}
			is RecentAction.BackPressed -> {
				viewModel.obtainEvent(RecentEvent.BackPressed)
			}
		}
	}

	private fun showData(data : List<Contact>){
		adapter.items = data
		adapter.notifyDataSetChanged()
	}
}