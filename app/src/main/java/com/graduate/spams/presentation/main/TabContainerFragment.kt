package com.graduate.spams.presentation.main

import android.os.Bundle
import android.view.View
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.graduate.spams.R
import com.graduate.spams.core.Screens
import com.graduate.spams.core.extensions.toast
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentTabBinding
import com.graduate.spams.di.LocalCiceroneHolder
import com.graduate.spams.presentation.common.RouterProvider
import toothpick.Scope
import toothpick.ktp.delegate.inject

class TabContainerFragment : BaseFragment<FragmentTabBinding>(), RouterProvider {

	private val navigator by lazy { AppNavigator(requireActivity(), R.id.tab_container, childFragmentManager) }

	private val ciceroneHolder: LocalCiceroneHolder by inject()

	private val containerName get() = arguments?.getString(CONTAINER_NAME) ?: ""

	private val cicerone: Cicerone<Router>
		get() = ciceroneHolder.getCicerone(containerName)

	override val router: Router
		get() = cicerone.router

	private val currentFragment: BaseFragment<*>?
		get() = childFragmentManager.fragments.firstOrNull { !it.isHidden} as? BaseFragment<*>


	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		if (childFragmentManager.findFragmentById(R.id.tab_container) == null){
			cicerone.router.replaceScreen(
				when (containerName){
					MainFlowFragment.TAB_SEARCH -> Screens.search()
					MainFlowFragment.TAB_RECENT-> Screens.recent()
					else -> throw NotImplementedError()
				}
			)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	override fun onResume() {
		super.onResume()
		cicerone.getNavigatorHolder().setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		cicerone.getNavigatorHolder().removeNavigator()
	}

	override fun onBackPressed() {
		currentFragment?.onBackPressed()
	}

	companion object {
		private const val CONTAINER_NAME = "container_name"

		fun newInstance(name: String) = TabContainerFragment().apply {
			arguments = Bundle().apply {
				putString(CONTAINER_NAME, name)
			}
		}
	}
}