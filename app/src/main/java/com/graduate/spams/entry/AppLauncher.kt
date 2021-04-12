package com.graduate.spams.entry

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.graduate.spams.presentation.main.MainFlowFragment
import javax.inject.Inject

//TODO: maybe delete
class AppLauncher @Inject constructor(
	private val router: Router
) {
	fun firstLaunch() = router.newRootScreen(getMainScreen())

	private fun getMainScreen() = FragmentScreen { MainFlowFragment() }

}