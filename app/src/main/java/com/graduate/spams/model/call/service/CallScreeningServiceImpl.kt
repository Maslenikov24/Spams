package com.graduate.spams.model.call.service

import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi
import com.graduate.spams.di.Scopes
import com.graduate.spams.model.preferences.call.CallPreferenceStorage
import com.graduate.spams.presentation.search.SearchInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
class CallScreeningServiceImpl : CallScreeningService() {

	// TODO: refactor to repository without saving to history
	private val interactor by inject<SearchInteractor>()

	private val dataStore by inject<CallPreferenceStorage>()

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
		Timber.tag("AppLog").v("viewModelLiveCircle onDestroy ${this::class.java}")
	}

	override fun onBind(intent: Intent?): IBinder? {
		initAppScope()
		return super.onBind(intent)
	}

	private fun initAppScope(){
		KTP.openScope(Scopes.APP_SCOPE)
			.openSubScope(Scopes.SCREENING_SERVICE_SCOPE)
			.inject(this)
	}

	override fun onUnbind(intent: Intent?): Boolean {
		return super.onUnbind(intent)
	}

	@RequiresApi(Build.VERSION_CODES.Q)
	override fun onScreenCall(call: Call.Details) {
		val phoneNumber = call.handle.schemeSpecificPart
		Timber.tag("AppLog").d(phoneNumber)

		//searchNumber(phoneNumber)

		val callDirection = call.callDirection
		Timber.tag("AppLog").d(callDirection.toString())

		if (dataStore.isNeedBlock) {
			// https://android.googlesource.com/platform/frameworks/base/+/master/telecomm/java/android/telecom/CallScreeningService.java
			val response = CallResponse.Builder()
				.setDisallowCall(dataStore.shouldDisallowCall)
				.setRejectCall(dataStore.shouldRejectCall)
				.setSkipCallLog(dataStore.shouldSkipCallLog)
				.setSkipNotification(dataStore.shouldSkipNotification)
				.setSilenceCall(dataStore.shouldSilenceCall)
				.build()
			respondToCall(call, response)
		}
	}

	private fun searchNumber(number: String){
		ui {
			val result = interactor.searchNumber(number)
			Timber.tag("AppLog").d(result.toString())
		}
	}
}