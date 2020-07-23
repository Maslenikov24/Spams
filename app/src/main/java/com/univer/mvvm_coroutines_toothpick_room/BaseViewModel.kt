package com.univer.mvvm_coroutines_toothpick_room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>: ViewModel() {

    protected val action: MutableLiveData<T> = MutableLiveData()

}