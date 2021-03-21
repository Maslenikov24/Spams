package com.univer.mvvm_coroutines_toothpick_room.presentation.detail

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.databinding.FragmentDetailBinding
import com.univer.mvvm_coroutines_toothpick_room.presentation.common.RouterProvider
import com.univer.mvvm_coroutines_toothpick_room.presentation.detail.models.DetailEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchEvent
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

	private val viewModel by inject<DetailViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules(module {
			bind<Router>().toInstance((parentFragment as RouterProvider).router)
		})
		scope.installViewModel<DetailViewModel>()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binging.text.setOnClickListener {
			viewModel.obtainEvent(DetailEvent.DetailRouteEvent)
		}
	}

	override fun onBackPressed(){
		viewModel.obtainEvent(DetailEvent.DetailBackPressedEvent)
	}
}