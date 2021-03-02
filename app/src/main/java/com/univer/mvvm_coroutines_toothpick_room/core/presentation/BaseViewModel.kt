package com.univer.mvvm_coroutines_toothpick_room.core.presentation

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import java.util.*

abstract class BaseViewModel<S, A, E>(private val navigatorHolder: NavigatorHolder? = null): ViewModel() {

    //action - _viewStates
    //state = viewStates()
    //MVI - M(State), V(View), I(Event)

    private val subscriptions = LinkedList<ReceiveChannel<*>>() //TODO: understand it
    private val supervisorJob = SupervisorJob() //TODO: understand it
    private val uiScope = CoroutineScope(Dispatchers.Main + supervisorJob)
    private val ioScope = CoroutineScope(Dispatchers.IO + supervisorJob)
    private val handler = CoroutineExceptionHandler { _, throwable ->
        Timber.i(throwable)
    }

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

    protected fun ui(block: suspend CoroutineScope.() -> Unit): Job =
        uiScope.launch { block(this) }

    protected fun io(block: suspend CoroutineScope.() -> Unit): Job =
        ioScope.launch { block(this) }

    init {
        Timber.e("viewModelLiveCircle start ${this::class.java}")
    }

    override fun onCleared() {
        super.onCleared()
        supervisorJob.cancelChildren()
        subscriptions.forEach { it.cancel() }
        Timber.e("viewModelLiveCircle onCleared ${this::class.java}")
    }

    fun addNavigator(navigator: Navigator) {
        navigatorHolder?.setNavigator(navigator)
    }

    fun removeNavigator() {
        navigatorHolder?.removeNavigator()
    }
}