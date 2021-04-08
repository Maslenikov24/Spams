package com.univer.mvvm_coroutines_toothpick_room.model.call.service

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallAudioState
import android.telecom.InCallService
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class InCallServiceImpl : InCallService() {
	override fun onBind(intent: Intent?): IBinder? {
		return super.onBind(intent)
	}

	override fun onUnbind(intent: Intent?): Boolean {
		return super.onUnbind(intent)
	}

	override fun onCallAudioStateChanged(audioState: CallAudioState?) {
		super.onCallAudioStateChanged(audioState)
	}

	override fun onBringToForeground(showDialpad: Boolean) {
		super.onBringToForeground(showDialpad)
	}

	override fun onCallAdded(call: Call?) {
		super.onCallAdded(call)
	}

	override fun onCallRemoved(call: Call?) {
		super.onCallRemoved(call)
	}

	override fun onCanAddCallChanged(canAddCall: Boolean) {
		super.onCanAddCallChanged(canAddCall)
	}

	override fun onSilenceRinger() {
		super.onSilenceRinger()
	}

	override fun onConnectionEvent(call: Call?, event: String?, extras: Bundle?) {
		super.onConnectionEvent(call, event, extras)
	}
}