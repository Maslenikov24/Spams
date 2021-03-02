package com.univer.mvvm_coroutines_toothpick_room.core

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.univer.mvvm_coroutines_toothpick_room.presentation.main.MainFlowFragment
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.RecentFragment
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.SearchFragment

object Screens {
	fun main() = FragmentScreen { MainFlowFragment() }
	fun search() = FragmentScreen { SearchFragment() }
	fun recent() = FragmentScreen { RecentFragment() }
}