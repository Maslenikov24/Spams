package com.univer.mvvm_coroutines_toothpick_room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel<T>: ViewModel() {

    protected abstract val defaultState: T

    protected val action: MutableLiveData<T> = MutableLiveData()

    val state: LiveData<T> get() = action

    val currentState: T
        get() = action.value?: defaultState

    init {
        Timber.e("viewModelLiveCircle start ${this::class.java}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("viewModelLiveCircle onCleared ${this::class.java}")
    }
}