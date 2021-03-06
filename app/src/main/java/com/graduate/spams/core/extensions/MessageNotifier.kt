package com.graduate.spams.core.extensions

import android.app.Activity
import android.app.Service
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun Fragment.toast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Activity.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Service.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.log(message: String) =
    Timber.i(message)

fun Activity.log(message: String) =
    Timber.i(message)

fun View.snack(message: String) =
   Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()