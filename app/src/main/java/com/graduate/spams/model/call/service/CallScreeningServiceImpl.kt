package com.graduate.spams.model.call.service

import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi
import com.graduate.spams.di.Scopes
import com.graduate.spams.model.call.provider.IncomingWindowProvider
import com.graduate.spams.model.preferences.call.CallPreferenceStorage
import com.graduate.spams.presentation.search.SearchInteractor
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

@RequiresApi(Build.VERSION_CODES.Q)
class CallScreeningServiceImpl : CallScreeningService() {

	// TODO: refactor to repository without saving to history
	private val interactor by inject<SearchInteractor>()

	private val dataStore by inject<CallPreferenceStorage>()
	private val incomingWindowProvider by inject<IncomingWindowProvider>()

	override fun onBind(intent: Intent?): IBinder? {
		initAppScope()
		return super.onBind(intent)
	}

	private fun initAppScope(){
		KTP.openScope(Scopes.APP_SCOPE)
			.openSubScope(Scopes.CALL_SCREENING_SERVICE_SCOPE)
			.inject(this)
	}

	override fun onUnbind(intent: Intent?): Boolean {
		return super.onUnbind(intent)
	}

	@RequiresApi(Build.VERSION_CODES.Q)
	override fun onScreenCall(call: Call.Details) {
		val phoneNumber = call.handle.schemeSpecificPart
		Timber.tag("AppLog").d(phoneNumber)

		//TODO: sync with isNeedBlock
		incomingWindowProvider.openWindow(phoneNumber)
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
}