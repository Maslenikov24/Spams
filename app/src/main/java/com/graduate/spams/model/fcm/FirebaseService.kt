package com.graduate.spams.model.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.graduate.spams.di.Scopes
import com.graduate.spams.model.auth.repository.AuthRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import java.util.*

class FirebaseService : FirebaseMessagingService() {

	private val repository by inject<AuthRepository>()

	private val subscriptions = LinkedList<ReceiveChannel<*>>()
	private val supervisorJob = SupervisorJob()
	private val uiScope = CoroutineScope(Dispatchers.Main.immediate + supervisorJob)
	private val handler = CoroutineExceptionHandler { _, throwable ->
		Timber.tag("AppLog").e(throwable)
	}

	private fun ui(block: suspend CoroutineScope.() -> Unit): Job =
		uiScope.launch(handler) { block(this) }

	override fun onDestroy() {
		super.onDestroy()
		supervisorJob.cancelChildren()
		subscriptions.forEach { it.cancel() }
		Timber.tag("AppLog").v("firebaseServiceLiveCircle onDestroy ${this::class.java}")
	}

	override fun onCreate() {
		initAppScope()
		super.onCreate()
	}

	private fun initAppScope(){
		KTP.openScope(Scopes.APP_SCOPE)
			.openSubScope(Scopes.FIREBASE_SERVICE_SCOPE)
			.inject(this)
	}

	override fun onMessageSent(message: String) {
		super.onMessageSent(message)
	}

	override fun onMessageReceived(message: RemoteMessage) {
		super.onMessageReceived(message)
	}

	override fun onNewToken(token: String) {
		ui {
			repository.sendToken(token)
		}
	}
}