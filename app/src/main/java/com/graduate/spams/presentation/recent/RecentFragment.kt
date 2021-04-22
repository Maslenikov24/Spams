package com.graduate.spams.presentation.recent

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.Permissions
import com.graduate.spams.core.extensions.*
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.data.contact.domain.Contact
import com.graduate.spams.databinding.FragmentRecentBinding
import com.graduate.spams.di.NestedRouter
import com.graduate.spams.presentation.common.RouterProvider
import com.graduate.spams.presentation.recent.adapter.RecentAdapter
import com.graduate.spams.presentation.recent.models.RecentAction
import com.graduate.spams.presentation.recent.models.RecentEvent
import com.graduate.spams.presentation.recent.models.RecentViewState
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
			bind<Router>().withName(NestedRouter::class).toInstance((parentFragment as RouterProvider).router)
			bind<RecentInteractor>().toClass<RecentInteractorImpl>()
			bind<(String, String?) -> Unit>().toInstance { number, name ->
				viewModel.obtainEvent(RecentEvent.OpenDetail(number, name))
			}
		})
		scope.installViewModel<RecentViewModel>()
	}

	@Suppress("DEPRECATION")
	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		permissions.forEachIndexed { index, it ->
			when (it){
				Permissions.READ_CALL_LOG -> {
					if (grantResults[index] != 0) {
						showPermissionLayout(true)
					}
					else {
						showPermissionLayout(false)
						viewModel.obtainEvent(RecentEvent.LoadRecent)
					}
				}
			}
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		subscribe(viewModel.viewStates(), ::renderViewState)
		subscribe(viewModel.viewActions(), ::renderAction)

		with(binging) {
			recyclerView.adapter = adapter

			toolbar.setNavigationOnClickListener {
				viewModel.obtainEvent(RecentEvent.OpenManage)
			}
			acceptPermission.setOnClickListener {
				allowPermission(requireContext(), Permissions.READ_CALL_LOG)
			}
		}
		if (this.checkPermission(requireContext(), Permissions.READ_CALL_LOG))
			viewModel.obtainEvent(RecentEvent.LoadRecent)
		else showPermissionLayout(true)
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

	private fun showPermissionLayout(isShow: Boolean){
		with(binging){
			recyclerView.visible(false)
			blockingLayout.visible(isShow)
		}
	}

	private fun showData(data : List<Contact>){
		with(binging){
			recyclerView.visible(data.isNotEmpty())
			emptyAnimationView.visible(data.isNullOrEmpty())
		}
		adapter.apply {
			items = data
			notifyDataSetChanged()
		}
	}
}