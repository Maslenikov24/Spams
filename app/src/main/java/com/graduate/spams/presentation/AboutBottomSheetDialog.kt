package com.graduate.spams.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.graduate.spams.BuildConfig
import com.graduate.spams.R
import com.graduate.spams.databinding.FragmentBottomAboutBinding

class AboutBottomSheetDialog : BottomSheetDialogFragment() {

	private val binding : FragmentBottomAboutBinding by viewBinding(createMethod = CreateMethod.INFLATE)

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		dialog?.window?.attributes?.windowAnimations = R.style.BottomSheetDialogAnimation

		val version = "Версия ${BuildConfig.VERSION_NAME}"
		binding.version.text = version
	}
}