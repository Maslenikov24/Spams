package com.graduate.spams.core.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.graduate.spams.core.Permissions

fun Fragment.allowPermissions(context: Context) {
	val permissions: MutableList<String> = mutableListOf()
	if (ContextCompat.checkSelfPermission(context, Permissions.READ_PHONE_STATE)
		!= PackageManager.PERMISSION_GRANTED){
		permissions.add(Permissions.READ_PHONE_STATE)
	}

	if (ContextCompat.checkSelfPermission(context, Permissions.READ_CALL_LOG)
		!= PackageManager.PERMISSION_GRANTED){
		permissions.add(Permissions.READ_CALL_LOG)
	}

	@Suppress("DEPRECATION")
	permissions.let {
		if (it.count() > 0){
			requestPermissions(it.toTypedArray(), 1)
		}
	}
}

fun Fragment.checkPermissions(context: Context) : Boolean {
	var allAllowed = true
	if (ContextCompat.checkSelfPermission(context, Permissions.READ_PHONE_STATE)
		!= PackageManager.PERMISSION_GRANTED){
		allAllowed = false
	}

	if (ContextCompat.checkSelfPermission(context, Permissions.READ_CONTACTS)
		!= PackageManager.PERMISSION_GRANTED){
		allAllowed = false
	}

	if (!Settings.canDrawOverlays(context)) allAllowed = false
	return allAllowed
}

fun Fragment.checkPermission(context: Context, permission: String) =
	ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED


fun Fragment.allowPermission(context: Context, permission: String) {
	val permissions: MutableList<String> = mutableListOf()
	if (ContextCompat.checkSelfPermission(context, permission)
		!= PackageManager.PERMISSION_GRANTED){
		permissions.add(permission)
	}

	@Suppress("DEPRECATION")
	permissions.let {
		if (it.count() > 0){
			requestPermissions(it.toTypedArray(), 1)
		}
	}
}