package com.graduate.spams.presentation.common.shape

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import com.graduate.spams.R

fun contentWindowShape(color: Int) : ShapeDrawable {
	val shape = ShapeDrawable(RoundRectShape(
		floatArrayOf(36f, 36f, 36f, 36f, 0f, 0f, 0f, 0f),
		null,
		null))
	shape.paint.color = color
	return shape
}

fun footerWindowShape(context: Context) : ShapeDrawable {
	val shape = ShapeDrawable(RoundRectShape(
		floatArrayOf(0f, 0f, 0f, 0f, 36f, 36f, 36f, 36f),
		null,
		null))
	shape.paint.color = context.getColor(R.color.windowFooter)
	return shape
}