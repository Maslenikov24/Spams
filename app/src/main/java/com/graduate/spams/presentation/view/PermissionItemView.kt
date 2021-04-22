package com.graduate.spams.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.graduate.spams.R
import com.graduate.spams.core.extensions.replaceTo
import com.graduate.spams.databinding.ViewPermissionBinding

class PermissionItemView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet ?= null,
	defStyle: Int = 0
): FrameLayout(context, attrs, defStyle){

	private val binding: ViewPermissionBinding by viewBinding(CreateMethod.BIND)

	init {
		inflate(context, R.layout.view_permission, this)

		val array = context.obtainStyledAttributes(attrs, R.styleable.PermissionItemView)
		val imageResource = array.getResourceId(R.styleable.PermissionItemView_permissionIcon, 0)
		with(binding){
			permissionName.text = array.getString(R.styleable.PermissionItemView_permissionName)
			permissionIcon.setImageResource(imageResource)
		}

	}

	fun setCheckedPermission(isChecked: Int){
		with(binding){
			if (isChecked == 0) switchButton.replaceTo(checkedIcon)
			else switchButton.isChecked = false
		}
	}

	fun onAllowedPermission(isAllowed: Boolean, listener: () -> Unit){
		if (isAllowed) {
			setCheckedPermission(0)
		}
		else {
			binding.switchButton.setOnCheckedChangeListener { _, isChecked ->
				if (isChecked) listener.invoke()
			}
		}
	}
}