package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import android.view.View

fun View.visible(isVisible: Boolean) {
	this.visibility = if (isVisible) View.VISIBLE else View.GONE
}
