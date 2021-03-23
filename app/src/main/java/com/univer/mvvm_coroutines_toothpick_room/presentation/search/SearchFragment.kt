package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.snack
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.subscribe
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.data.domain.number.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.databinding.FragmentSearchBinding
import com.univer.mvvm_coroutines_toothpick_room.presentation.common.RouterProvider
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.adapter.SearchAdapter
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.models.SearchViewState
import kotlinx.android.synthetic.main.fragment_search.*
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class SearchFragment : BaseFragment<FragmentSearchBinding>(){

	private val viewModel by inject<SearchViewModel>()
	private val adapter by inject<SearchAdapter>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules(module {
			bind<Router>().toInstance((parentFragment as RouterProvider).router)
		})
		scope.installViewModel<SearchViewModel>()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binging.button.setOnClickListener {
			val phoneNumber = binging.searchText.text.toString()
			viewModel.obtainEvent(SearchEvent.SearchNumber(phoneNumber))
		}

		subscribe(viewModel.viewStates(), ::renderViewState)
		subscribe(viewModel.viewActions(), ::renderAction)

		recyclerView.apply {
			adapter = this@SearchFragment.adapter
			setHasFixedSize(true)
		}
		viewModel.obtainEvent(SearchEvent.LoadHistory)
	}

	override fun onBackPressed(){
		viewModel.obtainEvent(SearchEvent.ConfirmExit)
	}

	private fun renderViewState(viewState: SearchViewState){
		when (viewState){
			is SearchViewState.ShowHistory -> {
				showData(viewState.data)
			}
		}
	}

	private fun renderAction(viewAction: SearchAction){
		when (viewAction){
			is SearchAction.ShowNotify -> {
				view?.snack("Confirm exit?")
			}
			is SearchAction.BackPressed -> {
				viewModel.obtainEvent(SearchEvent.BackPressed)
			}
		}
	}

	private fun showData(data: List<PhoneNumber>){
		adapter.items = data
		adapter.notifyDataSetChanged()
	}
}