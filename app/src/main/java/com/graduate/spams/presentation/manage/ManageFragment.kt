package com.graduate.spams.presentation.manage

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.graduate.spams.R
import com.graduate.spams.core.extensions.subscribe
import com.graduate.spams.core.extensions.toast
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentManageBinding
import com.graduate.spams.presentation.manage.models.ManageEvent
import com.graduate.spams.presentation.manage.models.ManageViewState
import toothpick.Scope
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module
import toothpick.ktp.delegate.inject

class ManageFragment : BaseFragment<FragmentManageBinding>() {

	private val viewModel by inject<ManageViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installModules( module {
			bind<ManageInteractor>().toClass<ManageInteractorImpl>()
		})
		scope.installViewModel<ManageViewModel>()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		subscribe(viewModel.viewStates(), ::renderViewState)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(binging){
			appBar.apply {
				setNavigationOnClickListener {
					onBackPressed()
				}
				setActionOnClickListener {
					/* nothing */
				}
			}

			uidInfo.setOnClickListener {
				showUidInfoDialog()
			}

			uidNumber.setOnClickListener(::copyUid)

			about.setOnItemClick {
				viewModel.obtainEvent(ManageEvent.OpenAbout)
			}
			permissions.setOnItemClick {
				viewModel.obtainEvent(ManageEvent.OpenPermissions)
			}
		}
	}

	private fun copyUid(view: View){
		val uid = (view as TextView).text
		val clipBoard = requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
		val clip = ClipData.newPlainText("label", uid) as ClipData
		clipBoard.setPrimaryClip(clip)
		toast("Uid скопирован в буфер обмена")
	}

	private fun showUidInfoDialog(){
		val dialog = MaterialAlertDialogBuilder(requireContext())
			.setMessage("Uid - уникальный идентификатор пользователя, с помощью которого осуществляется привязка пользователей и синхронизация данных")
			.setPositiveButton("Понятно") { _, _ ->  }
			.setNegativeButton("Не понятно") { _, _ -> showUidInfoDialog() }
			.create()

		dialog.window?.attributes?.windowAnimations = R.style.UidInfoDialogAnimation
		dialog.show()
	}

	private fun renderViewState(viewState: ManageViewState){
		when (viewState){
			is ManageViewState.ShowUid -> {
				//TODO: revert
				binging.uidNumber.text = viewState.uid
			}
		}
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(ManageEvent.BackPressed)
	}
}