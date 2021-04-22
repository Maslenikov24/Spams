package com.graduate.spams.core

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

abstract class EventBus<T>(type: Type = Type.PUBLISH) : MutableSharedFlow<T> by MutableSharedFlow(
	replay = when (type){
		Type.PUBLISH -> 0
		Type.BEHAVIOUR -> 1
	},
	extraBufferCapacity = 1,
	onBufferOverflow = BufferOverflow.DROP_OLDEST
) {

	fun sendMessage(message: T){
		tryEmit(message)
	}

	enum class Type{
		PUBLISH, BEHAVIOUR
	}

}