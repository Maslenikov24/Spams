package com.univer.mvvm_coroutines_toothpick_room.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class LocalCiceroneHolder {
	private val containers = HashMap<String, Cicerone<Router>>()

	fun getCicerone(containerTag: String): Cicerone<Router> =
		containers.getOrPut(containerTag){
			Cicerone.create()
		}
}