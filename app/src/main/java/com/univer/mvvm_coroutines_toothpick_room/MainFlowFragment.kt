package com.univer.mvvm_coroutines_toothpick_room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.GlobalScope

private const val ARG_PARAM1 = "param1"
class MainFlowFragment : Fragment() {

	private var param1: String? = null
	private var param2: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			param1 = it.getString(ARG_PARAM1)
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_main_flow, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	companion object {

		fun newInstance(param1: String, param2: String) =
			MainFlowFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_PARAM1, param1)
				}
			}
	}
}