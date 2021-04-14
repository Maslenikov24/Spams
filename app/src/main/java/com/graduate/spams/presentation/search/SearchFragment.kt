package com.graduate.spams.presentation.search

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Router
import com.graduate.spams.core.extensions.snack
import com.graduate.spams.core.extensions.subscribe
import com.graduate.spams.core.extensions.visible
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.data.history.domain.HistoryNumber
import com.graduate.spams.databinding.FragmentSearchBinding
import com.graduate.spams.di.Adapter
import com.graduate.spams.presentation.common.RouterProvider
import com.graduate.spams.presentation.search.adapter.SearchAdapter
import com.graduate.spams.presentation.search.models.SearchAction
import com.graduate.spams.presentation.search.models.SearchEvent
import com.graduate.spams.presentation.search.models.SearchViewState
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
			bind<SearchInteractor>().toClass<SearchInteractorImpl>()
			bind<(String) -> Unit>().withName(Adapter::class).toInstance { number ->
				viewModel.obtainEvent(SearchEvent.OpenDetail(number))
			}
			bind<(Long) -> Unit>().toInstance { id ->
				viewModel.obtainEvent(SearchEvent.DeleteFromHistory(id))
			}
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
			is SearchViewState.StartLoadingNumber -> {
				showProgress(true)
				binging.searchInputLayout.isErrorEnabled = false
			}
			is SearchViewState.ShowHistory -> {
				showData(viewState.data)
			}
			is SearchViewState.FinishedLoadingNumber -> {
				showProgress(false)
				binging.searchText.text?.clear()
				binging.recyclerView.post {
					binging.recyclerView.smoothScrollToPosition(0)
				}
			}
			is SearchViewState.FailedLoad -> {
				showProgress(false)
				viewState.message?.let {
					binging.searchInputLayout.error = it
				}
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

	private fun showProgress(show: Boolean){
		binging.progressBar.visible(show, false)
	}

	private fun showData(data: List<HistoryNumber>){
		adapter.items = data
		adapter.notifyDataSetChanged()
	}
}