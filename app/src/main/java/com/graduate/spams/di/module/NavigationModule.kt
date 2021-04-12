package com.graduate.spams.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.graduate.spams.di.LocalCiceroneHolder
import com.graduate.spams.entry.AppLauncher
import toothpick.ktp.binding.module
import toothpick.ktp.binding.bind

fun navigationModule() = module {

	val cicerone = Cicerone.create()

	bind<AppLauncher>().singleton()
	bind<Router>().toInstance(cicerone.router)
	bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())

	bind<LocalCiceroneHolder>().toInstance(LocalCiceroneHolder())
}