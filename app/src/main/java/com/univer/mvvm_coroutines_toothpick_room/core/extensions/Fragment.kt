package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.univer.mvvm_coroutines_toothpick_room.entry.AppActivity

fun Fragment.checkPermission(context: Context): Boolean {
	val permissions: MutableList<String>? = mutableListOf()
	val mainActivity = activity as AppActivity
	if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
		!= PackageManager.PERMISSION_GRANTED){
		//permissions?.add(Manifest.permission.READ_PHONE_STATE)
	}

	if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG)
		!= PackageManager.PERMISSION_GRANTED){
		permissions?.add(Manifest.permission.READ_CALL_LOG)
	}

	if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
		!= PackageManager.PERMISSION_GRANTED){
		//permissions?.add(Manifest.permission.READ_CONTACTS)
	}

	permissions?.let {
		if (it.count() > 0){
			requestPermissions(it.toTypedArray(), 1)
		}
		else return true
	}
	return false
}