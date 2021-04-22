package com.graduate.spams.core

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.graduate.spams.presentation.about.AboutBottomSheetDialog
import com.graduate.spams.presentation.manage.ManageFragment
import com.graduate.spams.presentation.detail.DetailFragment
import com.graduate.spams.presentation.main.MainFlowFragment
import com.graduate.spams.presentation.permissions.PermissionsFragment
import com.graduate.spams.presentation.recent.RecentFragment
import com.graduate.spams.presentation.recent.RecentStartParams
import com.graduate.spams.presentation.search.SearchFragment

object Screens {
	fun main() = FragmentScreen { MainFlowFragment() }
	fun search() = FragmentScreen { SearchFragment() }
	fun recent() = FragmentScreen { RecentFragment() }
	fun detail(number: String, name: String? = null) = FragmentScreen {
		DetailFragment.newInstance(RecentStartParams(number, name))
	}
	fun manage() = FragmentScreen { ManageFragment() }
	fun permissions() = FragmentScreen { PermissionsFragment() }
	fun about() = FragmentScreen { AboutBottomSheetDialog() }
}