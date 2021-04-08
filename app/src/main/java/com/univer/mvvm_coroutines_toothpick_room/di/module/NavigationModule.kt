package com.univer.mvvm_coroutines_toothpick_room.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.univer.mvvm_coroutines_toothpick_room.di.LocalCiceroneHolder
import com.univer.mvvm_coroutines_toothpick_room.entry.AppLauncher
import toothpick.ktp.binding.module
import toothpick.ktp.binding.bind

fun navigationModule() = module {

	val cicerone = Cicerone.create()

	bind<AppLauncher>().singleton()
	bind<Router>().toInstance(cicerone.router)
	bind<NavigatorHolder>().toInstance(cicerone.getNavigatorHolder())

	bind<LocalCiceroneHolder>().toInstance(LocalCiceroneHolder())
}