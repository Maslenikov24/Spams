@file:Suppress("DEPRECATION")

package com.graduate.spams.model.call.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.telephony.TelephonyManager
import android.widget.Toast
import com.graduate.spams.core.CallListener
import com.graduate.spams.di.Scopes
import com.graduate.spams.model.call.provider.IncomingWindowProvider
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject

class DeprecatedCallService : BroadcastReceiver() {

	private var isCalling = false
	//TODO: mb create new interactor
	private val callListener by inject<CallListener>()
	private val incomingWindowProvider by inject<IncomingWindowProvider>()

	override fun onReceive(context: Context?, intent: Intent?) {
		initAppScope()
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
			//TODO: check for permissions
			val phoneNumber = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
			phoneNumber?.let {
				if (intent.action.equals("android.intent.action.PHONE_STATE")){
					val phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
					phoneState?.let{
						isCalling = true
						when (phoneState){
							/**
							 * Трубка не поднята, телефон звонит
							 */
							TelephonyManager.EXTRA_STATE_RINGING -> {
								incomingWindowProvider.openWindow(phoneNumber)
							}
							/**
							 * Телефон находится в режиме звонка (набор номера при исходящем звонке / разговор)
							 */
							TelephonyManager.EXTRA_STATE_OFFHOOK -> { /* nothing */}

							/**
							 * Телефон находится в ждущем режиме - это событие наступает по окончанию разговора
							 * или в ситуации "отказался поднимать трубку и сбросил звонок".
							 */
							TelephonyManager.EXTRA_STATE_IDLE -> {
								if (isCalling) {
									callListener.sendMessage(false)
									Timber.e("Close spam")
									Toast.makeText(context, "Конец разговора", Toast.LENGTH_SHORT)
										.show()
								}
							}
						}
					}
				}
			}
		}
	}

	override fun peekService(myContext: Context?, service: Intent?): IBinder {
		return super.peekService(myContext, service)
	}

	private fun initAppScope(){
		KTP.openScope(Scopes.APP_SCOPE)
			.openSubScope(Scopes.DEPRECATED_CALL_SERVICE_SCOPE)
			.inject(this)
	}
}