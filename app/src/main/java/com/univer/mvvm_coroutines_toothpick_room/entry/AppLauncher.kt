package com.univer.mvvm_coroutines_toothpick_room.entry

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.MainFlowFragment
import javax.inject.Inject

//TODO: maybe delete
class AppLauncher @Inject constructor(
	private val router: Router
) {
	fun firstLaunch() = router.newRootScreen(getMainScreen())

	private fun getMainScreen() = FragmentScreen { MainFlowFragment() }

}