package com.graduate.spams.core

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import java.util.*

abstract class CoroutinesHelper {
	//TODO Read about LinkedList
	private val subscriptions = LinkedList<ReceiveChannel<*>>()

	//TODO Read about SupervisorJob
	private val supervisorJob = SupervisorJob()
	private val uiScope = CoroutineScope(Dispatchers.Main + supervisorJob)
	private val ioScope = CoroutineScope(Dispatchers.IO + supervisorJob)
	private val handler = CoroutineExceptionHandler { _, throwable ->
		onError(throwable)
	}

	protected fun onDestroy() {
		supervisorJob.cancelChildren()
		subscriptions.forEach {
			it.cancel()
		}
	}

	protected fun ui(block: suspend CoroutineScope.() -> Unit): Job =
		uiScope.launch(handler) { block(this) }

	protected fun io(block: suspend CoroutineScope.() -> Unit): Job =
		ioScope.launch(handler) { block(this) }


	private fun onError(throwable: Throwable) {
		//throw throwable
	}
}