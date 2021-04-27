package com.graduate.spams.model.auth.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.graduate.spams.core.extensions.fetchData
import com.graduate.spams.data.auth.net.AuthResponse
import com.graduate.spams.data.auth.net.LoginRequest
import com.graduate.spams.di.IO
import com.graduate.spams.di.MacAddress
import com.graduate.spams.model.auth.net.service.AuthService
import com.graduate.spams.model.preferences.PreferencesKeys
import com.graduate.spams.model.preferences.app.AppPreferenceStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	@IO private val ioDispatcher: CoroutineDispatcher,
	private val appPreferenceStorage: AppPreferenceStorage,
	private val authService: AuthService,
	@MacAddress private val macAddress: String
): AuthRepository {

	override suspend fun initToken() {
		withContext(ioDispatcher){
			FirebaseMessaging.getInstance().token.addOnCompleteListener {
				if (!it.isSuccessful) {
					Timber.tag("AppLog").e(it.exception)
					return@addOnCompleteListener
				}
			}
		}
	}

	override suspend fun sendToken(token: String){
		withContext(ioDispatcher) {
			val result = authService.sendToken(token, macAddress).fetchData()
			Timber.tag("AppLog").d(result.toString())
			result?.let {
				saveAuthData(result)
			}
		}
	}

	private suspend fun saveAuthData(data: AuthResponse){
		with(appPreferenceStorage){
			setValue(PreferencesKeys.App.UID, data.uid)
			setValue(PreferencesKeys.App.REGISTRATION_TOKEN, data.registrationToken)
		}
	}

	override suspend fun login() {
		withContext(ioDispatcher){
			with(appPreferenceStorage){
				uid.collectLatest { uid ->
					registrationToken.collectLatest { token ->
						if (uid.isNotEmpty() && token.isNotEmpty()) {
							val request = LoginRequest(uid = uid, registrationToken = token)
							val result = authService.login(request).fetchData()
							result?.sessionToken?.let {
								setValue(PreferencesKeys.App.SESSION_TOKEN, result.sessionToken)
								Timber.tag("AppLog").d("Session token: $sessionToken")
							}
						}
					}
				}
			}
		}
	}
}