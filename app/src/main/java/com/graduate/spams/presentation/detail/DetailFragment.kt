package com.graduate.spams.presentation.detail

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.extensions.subscribe
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentDetailBinding
import com.graduate.spams.presentation.common.RouterProvider
import com.graduate.spams.presentation.detail.models.DetailAction
import com.graduate.spams.presentation.detail.models.DetailEvent
import com.graduate.spams.presentation.detail.models.DetailViewState
import com.graduate.spams.presentation.recent.RecentStartParams
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

	private val viewModel by inject<DetailViewModel>()

	private val phoneNumber get() = arguments?.getString(ARG_NUMBER)
	private val name get() = arguments?.getString(ARG_NAME)

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules(module {
			bind<Router>().toInstance((parentFragment as RouterProvider).router)
			val initParams = DetailViewModel.InitParams(phoneNumber)
			bind<DetailViewModel.InitParams>().toInstance(initParams)
			bind<DetailInteractor>().toClass<DetailInteractorImpl>()
		})
		scope.installViewModel<DetailViewModel>()
	}

	//TODO: toolbar!
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		subscribe(viewModel.viewStates(), ::renderViewState)
		subscribe(viewModel.viewActions(), ::renderAction)
		binging.number.setOnClickListener {
			viewModel.obtainEvent(DetailEvent.DetailRouteEvent)
		}
		binging.number.text = phoneNumber
		binging.name.text = name

		binging.toolbar.setNavigationOnClickListener {
			onBackPressed()
		}
	}

	private fun renderViewState(viewState: DetailViewState) {
	    when (viewState){
	        is DetailViewState.StartLoadingNumber -> {

	        }
			is DetailViewState.ShowDetail -> {
				//binging.number.text = viewState.phoneNumber.rating.toString()
			}
			is DetailViewState.FinishedLoadingNumber -> {

			}
			is DetailViewState.FailedLoad -> {

			}
	    }
	}

	private fun renderAction(viewAction: DetailAction) {

	}


	override fun onBackPressed() {
		viewModel.obtainEvent(DetailEvent.DetailBackPressedEvent)
	}

	companion object {
		const val ARG_NUMBER = "arg_number"
		const val ARG_NAME = "arg_name"

		fun newInstance(params: RecentStartParams) = DetailFragment().apply {
			arguments = Bundle().apply {
				putString(ARG_NUMBER, params.number)
				putString(ARG_NAME, params.name)
			}
		}
	}
}