package com.univer.mvvm_coroutines_toothpick_room.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

abstract class BaseViewModel<S, A, E>: ViewModel() {

    //action - _viewStates
    //state = viewStates()
    //MVI - M(State), V(View), I(Event)

    //protected abstract val defaultState: S

    private val _viewStates: MutableStateFlow<S?> = MutableStateFlow(null)
    fun viewStates(): StateFlow<S?> = _viewStates

    protected var viewState: S
        get() = _viewStates.value
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            /** StateFlow doesn't work with same values */
            if (_viewStates.value == value) {
                _viewStates.value = null
            }

            _viewStates.value = value
        }

    private var _viewActions: MutableStateFlow<A?> = MutableStateFlow(null)
    fun viewActions(): StateFlow<A?> = _viewActions

    protected var viewAction: A
        get() = _viewActions.value
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            //_viewAction = value
            if (_viewActions.value == value) {
                _viewActions.value = null
            }

            _viewActions.value = value
        }

    abstract fun obtainEvent(viewEvent: E)

    init {
        Timber.e("viewModelLiveCircle start ${this::class.java}")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("viewModelLiveCircle onCleared ${this::class.java}")
    }
}