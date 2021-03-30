package com.univer.mvvm_coroutines_toothpick_room.core.extensions

import android.view.View

fun View.visible(isVisible: Boolean, isGone: Boolean = true) {
	this.visibility =
		if (isVisible) View.VISIBLE
		else {
			if (isGone) View.GONE
			else View.INVISIBLE
		}
}
