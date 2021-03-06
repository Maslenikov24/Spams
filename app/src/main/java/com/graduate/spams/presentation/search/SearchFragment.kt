package com.graduate.spams.presentation.search

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.github.terrakok.cicerone.Router
import com.graduate.spams.R
import com.graduate.spams.core.SearchHistoryDiffUtil
import com.graduate.spams.core.extensions.*
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.data.history.domain.HistoryNumber
import com.graduate.spams.databinding.FragmentSearchBinding
import com.graduate.spams.di.Adapter
import com.graduate.spams.di.NestedRouter
import com.graduate.spams.presentation.common.RouterProvider
import com.graduate.spams.presentation.search.adapter.SearchAdapter
import com.graduate.spams.presentation.search.models.SearchAction
import com.graduate.spams.presentation.search.models.SearchEvent
import com.graduate.spams.presentation.search.models.SearchViewState
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
			bind<Router>().withName(NestedRouter::class).toInstance((parentFragment as RouterProvider).router)
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

		subscribe(viewModel.viewStates(), ::renderViewState)
		subscribe(viewModel.viewActions(), ::renderAction)
		viewModel.obtainEvent(SearchEvent.LoadHistory)

		with(binging){
			searchButton.apply {
				setColorFilter(requireContext().getColor(R.color.primary75), PorterDuff.Mode.SRC_IN)
				setOnClickListener {
					val phoneNumber = binging.searchInput.text.toString()
					if (phoneNumber.isNotBlank()) viewModel.obtainEvent(SearchEvent.SearchNumber(phoneNumber))
					//TODO: hide keyboard
				}
			}

			recyclerView.apply {
				adapter = this@SearchFragment.adapter
				setHasFixedSize(true)
			}

			clearHistory.setOnClickListener {
				viewModel.obtainEvent(SearchEvent.DeleteAllHistory)
			}

			toolbar.setNavigationOnClickListener {
				viewModel.obtainEvent(SearchEvent.OpenManage)
			}

			floatingActionButton.setOnClickListener {
				viewModel.obtainEvent(SearchEvent.OpenPermissions)
			}
			if (checkPermissions(requireContext())) floatingActionButton.visible(false)
		}
	}

	override fun onBackPressed(){
		viewModel.obtainEvent(SearchEvent.ConfirmExit)
	}

	private fun renderViewState(viewState: SearchViewState){
		when (viewState){
			is SearchViewState.StartLoadingNumber -> {
				showProgress(true)
			}
			is SearchViewState.ShowHistory -> {
				showData(viewState.data)
				binging.animationView.visible(false)
			}
			is SearchViewState.EmptyHistory -> {
				showData(emptyList())
				binging.animationView.visible(true)
			}
			is SearchViewState.FinishedLoadingNumber -> {
				showProgress(false)
				binging.searchInput.text?.clear()
				binging.recyclerView.post {
					binging.recyclerView.smoothScrollToPosition(0)
				}
			}
			is SearchViewState.FailedLoad -> {
				showProgress(false)
				viewState.message?.let {
					view?.snack(it)
				}
			}
			is SearchViewState.HideFloatingButton -> {
				binging.floatingActionButton.visible(false)
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
		binging.progressBar.visible(show)
		binging.searchButton.visible(!show,false)
	}

	private fun showData(data: List<HistoryNumber>) {
		val diffUtilCallback = SearchHistoryDiffUtil(data, adapter.items ?: emptyList())
		val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
		adapter.items = data
		diffResult.dispatchUpdatesTo(adapter)
	}
}