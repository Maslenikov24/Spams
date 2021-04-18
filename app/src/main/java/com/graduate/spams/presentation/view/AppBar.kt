package com.graduate.spams.presentation.view

import android.content.Context
import android.util.AttributeSet
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.appbar.AppBarLayout
import com.graduate.spams.R
import com.graduate.spams.databinding.ViewAppbarBinding

class AppBar @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet ?= null,
	defStyle: Int = 0
) : AppBarLayout(context, attrs, defStyle) {

	private val binding: ViewAppbarBinding by viewBinding(CreateMethod.BIND)

	init {
		inflate(context, R.layout.view_appbar, this)

		val array = context.obtainStyledAttributes(attrs, R.styleable.AppBar)
		val navigationIcon = array.getResourceId(R.styleable.AppBar_navigationIcon, 0)
		val actionIcon = array.getResourceId(R.styleable.AppBar_actionIcon, 0)
		with(binding) {
			title.text = array.getString(R.styleable.AppBar_title)
			this.navigationIcon.setImageResource(navigationIcon)
			this.actionIcon.setImageResource(actionIcon)
		}
	}

	fun setNavigationOnClickListener(listener: () -> Unit){
		binding.navigationIcon.setOnClickListener {
			listener.invoke()
		}
	}

	fun setActionOnClickListener(listener: () -> Unit){
		binding.actionIcon.setOnClickListener {
			listener.invoke()
		}
	}
}