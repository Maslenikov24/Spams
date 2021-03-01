package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.*

fun <T: Any?, SF: StateFlow<T?>> Fragment.subscribe(stateFlow: SF, block: (T) -> Unit) =
    lifecycleScope.launchWhenStarted {
        stateFlow.filterNotNull().collect { block(it) }
    }

fun <T: Any?, SF: StateFlow<T?>> AppCompatActivity.subscribe(stateFlow: SF, block: (T) -> Unit) =
    lifecycleScope.launchWhenStarted {
        stateFlow.filterNotNull().collect { block(it) }
    }