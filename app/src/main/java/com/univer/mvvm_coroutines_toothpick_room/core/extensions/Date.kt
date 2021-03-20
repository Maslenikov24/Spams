package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import android.annotation.SuppressLint
import java.util.*
import kotlin.math.abs

@SuppressLint("SimpleDateFormat")
fun Long.toDate(): String {
	val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
	return sdf.format(Date(this))
}

// TODO: refactor
@SuppressLint("SimpleDateFormat")
fun Long.toDateWithNormalization(): String{
	val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
	val dateToday = sdf.format(Date())
	var dateOfCall = sdf.format(Date(this))
	if (dateToday.substring(0,10) == dateOfCall.substring(0,10)){
		if (dateToday.substring(0, 1) == dateOfCall.substring(0, 1)) dateOfCall = "Сегодня"
		else if (abs(dateToday.substring(0, 1).toInt() - dateOfCall.substring(0, 1).toInt()) == 1)
			dateOfCall = "Вчера"
		//Regex("""[0-9]{2}/[0-9]{2}/[0-9]{4}"""), "Вчера")
	}
	else dateOfCall = dateOfCall.substring(0,10)
	return dateOfCall
}

@SuppressLint("SimpleDateFormat")
fun Long.toTime() = this.toDate().substring(11,16)