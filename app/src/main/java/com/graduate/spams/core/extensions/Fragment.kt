package com.graduate.spams.core.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.graduate.spams.core.Permissions

fun Fragment.checkPermission(context: Context): Boolean {
	val permissions: MutableList<String> = mutableListOf()
	if (ContextCompat.checkSelfPermission(context, Permissions.READ_PHONE_STATE)
		!= PackageManager.PERMISSION_GRANTED){
		permissions.add(Permissions.READ_PHONE_STATE)
	}

	if (ContextCompat.checkSelfPermission(context, Permissions.READ_CALL_LOG)
		!= PackageManager.PERMISSION_GRANTED){
		permissions.add(Permissions.READ_CALL_LOG)
	}

	permissions.let {
		if (it.count() > 0){
			requestPermissions(it.toTypedArray(), 1)
		}
		else return true
	}
	return false
}