package com.graduate.spams.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

fun <T: Any?, SF: StateFlow<T?>> Fragment.subscribe(stateFlow: SF, block: (T) -> Unit) =
    lifecycleScope.launchWhenStarted {
        stateFlow.filterNotNull().collect { block(it) }
    }

fun <T: Any?, SF: StateFlow<T?>> AppCompatActivity.subscribe(stateFlow: SF, block: (T) -> Unit) =
    lifecycleScope.launchWhenStarted {
        stateFlow.filterNotNull().collect { block(it) }
    }

fun<T> Flow<T>.tryCollectAsState(defaultValue: T): T? {
    var result: T? = defaultValue
    CoroutineScope(Dispatchers.IO).launch {
        this@tryCollectAsState.collect {
            result = it
        }
    }
    return result
}

suspend fun<T> Flow<T>.collectAsState(defaultValue: T): T {
    var result: T = defaultValue
    this.collect {
        result = it
    }
    return result
}