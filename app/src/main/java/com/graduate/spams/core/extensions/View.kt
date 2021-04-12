package com.graduate.spams.core.extensions

import android.view.View

fun View.visible(isVisible: Boolean, isGone: Boolean = true) {
	this.visibility =
		if (isVisible) View.VISIBLE
		else {
			if (isGone) View.GONE
			else View.INVISIBLE
		}
}
