package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import android.annotation.SuppressLint
import timber.log.Timber
import java.util.*
import kotlin.math.abs

@SuppressLint("SimpleDateFormat")
fun Long.toDate(): String {
	val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
	return sdf.format(Date(this))
}

// TODO: refactor
@SuppressLint("SimpleDateFormat")
fun Long.toDateWithNormalization(): String {
	// date
	var sdf = java.text.SimpleDateFormat("dd/MM/yyyy")
	val dateToday = sdf.format(Date())
	var dateOfCall = sdf.format(Date(this))

	// day
	sdf = java.text.SimpleDateFormat("dd")
	val dayToday = sdf.format(Date())
	val dayOfCall = sdf.format(Date(this))

	if (dateOfCall.substring(3, 10) == dateToday.substring(3, 10)) {
		if (dayToday == dayOfCall) dateOfCall = "Сегодня"
		else if (dayToday.toInt() - dayOfCall.toInt() == 1) dateOfCall = "Вчера"
	}
	return dateOfCall
}

@SuppressLint("SimpleDateFormat")
fun Long.toTime(): String {
	val sdf = java.text.SimpleDateFormat("HH:mm")
	return sdf.format(Date(this))
}