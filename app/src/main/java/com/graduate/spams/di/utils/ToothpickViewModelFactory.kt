package com.graduate.spams.di.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import toothpick.Toothpick

//TODO: understand it
class ToothpickViewModelFactory(private val scopeName: Any) : ViewModelProvider.NewInstanceFactory() {
	override fun <T : ViewModel?> create(clazz: Class<T>): T {
		return Toothpick.openScope(scopeName).getInstance(clazz) as T
	}
}