package com.univer.mvvm_coroutines_toothpick_room.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class EventBus<T>(){

	val scope = CoroutineScope(IO)

	private val _mStates: MutableStateFlow<T?> = MutableStateFlow(null)
	fun mStates(): StateFlow<T?> = _mStates

	protected var item: T
		get() = _mStates.value
			?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
		set(value) {
			/** StateFlow doesn't work with same values */
			if (_mStates.value == value) {
				_mStates.value = null
			}

			_mStates.value = value
		}

	fun saveItem(block: (T?) -> Unit){
		scope.launch {
			mStates().collect { block(it) }
		}
	}

}