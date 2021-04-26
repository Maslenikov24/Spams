package com.graduate.spams.core.extensions

import android.database.Cursor

fun Cursor.getString(param: String) = this.getString(this.getColumnIndex(param))

fun Cursor.getInt(param: String) = this.getInt(this.getColumnIndex(param))

fun Cursor.getLong(param: String) = this.getLong(this.getColumnIndex(param))