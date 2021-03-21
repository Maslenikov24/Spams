package com.univer.mvvm_coroutines_toothpick_room

import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallScreeningService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class CallScreeningServiceImpl : CallScreeningService() {
	override fun onBind(intent: Intent?): IBinder? {
		return super.onBind(intent)
	}

	override fun onUnbind(intent: Intent?): Boolean {
		return super.onUnbind(intent)
	}

	override fun onScreenCall(call: Call.Details) {
		/*val response = CallResponse.Builder()
			.setRejectCall(true)
			.build()
		toast("onScreenCall")
		respondToCall(call, response)*/
	}
}