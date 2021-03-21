package com.univer.mvvm_coroutines_toothpick_room.presentation.main

import android.os.Bundle
import android.view.View
import com.univer.mvvm_coroutines_toothpick_room.R
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.subscribe
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toast
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.databinding.FragmentMainFlowBinding
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowAction
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowEvent
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.models.MainFlowViewState
import toothpick.Scope
import toothpick.ktp.delegate.inject

class MainFlowFragment : BaseFragment<FragmentMainFlowBinding>(), MainFlowView {

	private val viewModel by inject<MainFlowViewModel>()

	private val currentFragment: BaseFragment<*>?
		get() = childFragmentManager.fragments.firstOrNull { !it.isHidden} as? BaseFragment<*>

	override fun onBackPressed() {
		currentFragment?.onBackPressed()
	}

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installViewModel<MainFlowViewModel>()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		configureBottomTabs()
		selectTab(currentFragment?.tag?: TAB_SEARCH)
		subscribe(viewModel.viewStates(), ::renderViewState)
		subscribe(viewModel.viewActions(), ::renderActions)
	}

	override fun renderViewState(viewState: MainFlowViewState) {
		when (viewState){
			is MainFlowViewState.SetToolbarWith -> {
				binging.toolbar.title = viewState.title
			}
		}
	}

	override fun renderActions(viewActions: MainFlowAction) {
		when (viewActions){
			is MainFlowAction.MainFlowShowNotifyAction -> {
				toast("Точно?")
			}
			is MainFlowAction.MainFlowBackPressedAction -> {
				currentFragment?.onBackPressed()
			}
		}
	}

	private fun configureBottomTabs(){
		binging.bottomNavigationView.apply {
			setOnNavigationItemSelectedListener {
				when (it.itemId){
					R.id.search -> {
						binging.toolbar.title = "Поиск номера"
						selectTab(TAB_SEARCH)
					}
					R.id.recent -> {
						binging.toolbar.title = "Список звонков"
						selectTab(TAB_RECENT)
					}
				}
				true
			}
		}
	}

	private fun selectTab(key: String){
		val currentFragment = currentFragment
		val newFragment = childFragmentManager.findFragmentByTag(key) // TODO: understand it

		if (currentFragment != null && newFragment != null && currentFragment == newFragment){
			return
		}

		childFragmentManager.beginTransaction().apply {
			if (newFragment == null){
				add(R.id.container, getTabFragment(key), key)
			}

			currentFragment?.let {
				hide(it)
			}
			newFragment?.let{
				show(it)
			}
		}.commitNow() // TODO: understand changes between commit() & commitNow()
	}

	private fun getTabFragment(key: String) = TabContainerFragment.newInstance(key)

	companion object {
		const val TAB_SEARCH = "tab_search"
		const val TAB_RECENT = "tab_recent"
	}
}