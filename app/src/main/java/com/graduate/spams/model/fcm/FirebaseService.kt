package com.graduate.spams.model.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.graduate.spams.BuildConfig
import com.graduate.spams.R
import com.graduate.spams.di.Scopes
import com.graduate.spams.entry.AppActivity
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

	companion object {
		private const val DEFAULT_NOTIFICATION_CHANNEL_ID = "${BuildConfig.APPLICATION_ID}.DEFAULT"
	}

	override fun onMessageReceived(remoteMessage: RemoteMessage) {
		super.onMessageReceived(remoteMessage)

		val notificationBuilder = NotificationCompat.Builder(this, DEFAULT_NOTIFICATION_CHANNEL_ID)
			.setContentTitle(remoteMessage.notification?.title)
			.setContentText(remoteMessage.notification?.body)
			.setPriority(NotificationCompat.PRIORITY_HIGH)
			.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
			.setSmallIcon(R.drawable.ic_admin_panel)
			//.setColor(ContextCompat.getColor(applicationContext, R.color.colorNotification))
			.apply {
				val uid = remoteMessage.data[NotificationHandler.UID]
				Timber.tag("AppLog").d(uid)
				val intent = Intent(applicationContext, AppActivity::class.java)
				intent.putExtra(NotificationHandler.UID, uid)
				val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
				setContentIntent(pendingIntent)
			}
			.setAutoCancel(true)

		val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

		notificationManager.notify(0, notificationBuilder.build())
	}

	override fun onNewToken(token: String) {
		ui {
			repository.sendToken(token)
		}
	}
}