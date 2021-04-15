package com.graduate.spams.presentation.detail

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentDetailBinding
import com.graduate.spams.presentation.common.RouterProvider
import com.graduate.spams.presentation.detail.models.DetailEvent
import com.graduate.spams.presentation.recent.RecentStartParams
import com.graduate.spams.presentation.search.models.SearchEvent
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
		})
		scope.installViewModel<DetailViewModel>()
	}

	//TODO: toolbar!
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binging.text.setOnClickListener {
			viewModel.obtainEvent(DetailEvent.DetailRouteEvent)
		}
		binging.text.text = phoneNumber
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