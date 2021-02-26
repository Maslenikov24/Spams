package com.univer.mvvm_coroutines_toothpick_room.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

suspend fun <T: Any, SF: SharedFlow<T?>> Fragment.subscribe(sharedFlow: SF, block: (T) -> Unit) =
    sharedFlow.collect {
        state -> state?.let {block(it)}
    }

suspend fun <T: Any?, SF: SharedFlow<T?>> AppCompatActivity.subscribe(sharedFlow: SF, block: (T) -> Unit) =
    sharedFlow.collect {
            state -> state?.let {block(it)}
    }