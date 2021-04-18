package com.graduate.spams.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.graduate.spams.R
import com.graduate.spams.databinding.ViewManageBinding

class ManageItemView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet ?= null,
	defStyle: Int = 0
): LinearLayout(context, attrs, defStyle){

	private val binding: ViewManageBinding by viewBinding(CreateMethod.BIND)

	init {
		inflate(context, R.layout.view_manage, this)

		val array = context.obtainStyledAttributes(attrs, R.styleable.ManageItemView)
		val imageResource = array.getResourceId(R.styleable.ManageItemView_icon, 0)
		with(binding){
			title.text = array.getString(R.styleable.ManageItemView_text)
			image.setImageResource(imageResource)
		}

	}
}