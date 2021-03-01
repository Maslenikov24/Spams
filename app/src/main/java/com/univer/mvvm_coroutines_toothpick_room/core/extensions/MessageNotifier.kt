package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import timber.log.Timber

fun Fragment.toast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Activity.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.log(message: String) =
    Timber.i(message)

fun Activity.log(message: String) =
    Timber.i(message)