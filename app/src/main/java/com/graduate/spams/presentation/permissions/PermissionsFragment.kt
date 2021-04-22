package com.graduate.spams.presentation.permissions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.graduate.spams.core.Permissions
import com.graduate.spams.core.extensions.*
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.databinding.FragmentPermissionsBinding
import com.graduate.spams.presentation.permissions.models.PermissionsEvent
import timber.log.Timber
import toothpick.Scope
import toothpick.ktp.delegate.inject

class PermissionsFragment : BaseFragment<FragmentPermissionsBinding>() {

	private val viewModel by inject<PermissionViewModel>()

	override fun installModules(scope: Scope) {
		super.installModules(scope)
		scope.installViewModel<PermissionViewModel>()
	}

	@Suppress("DEPRECATION")
	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		permissions.forEachIndexed { index, it ->
			when (it){
				Permissions.READ_PHONE_STATE -> {
					binging.readPhoneState.setCheckedPermission(grantResults[index])
				}
				Permissions.READ_CONTACTS -> {
					binging.readContacts.setCheckedPermission(grantResults[index])
				}
				Permissions.READ_CALL_LOG -> {
					binging.readCallLog.setCheckedPermission(grantResults[index])
				}
			}
		}
		if (checkPermissions(requireContext())) viewModel.obtainEvent(PermissionsEvent.HideSearchFloatingButton)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION){
			val isChecked = if (Settings.canDrawOverlays(requireContext())) 0 else 1
			binging.systemWindow.setCheckedPermission(isChecked)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		with(binging){
			readPhoneState.onAllowedPermission(checkPermission(requireContext(), Permissions.READ_PHONE_STATE)) {
				allowPermission(requireContext(), Permissions.READ_PHONE_STATE)
			}
			readContacts.onAllowedPermission(checkPermission(requireContext(), Permissions.READ_CONTACTS)) {
				allowPermission(requireContext(), Permissions.READ_CONTACTS)
			}
			readCallLog.onAllowedPermission(checkPermission(requireContext(), Permissions.READ_CALL_LOG)) {
				allowPermission(requireContext(), Permissions.READ_CALL_LOG)
			}
			systemWindow.onAllowedPermission(Settings.canDrawOverlays(requireContext())) {
				val intent = Intent(
					Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
					Uri.parse("package:" + requireContext().applicationContext.packageName)
				)
				startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION)
			}
			toolbar.setNavigationOnClickListener {
				onBackPressed()
			}
		}
	}

	override fun onBackPressed() {
		viewModel.obtainEvent(PermissionsEvent.OnBackPressed)
	}

	companion object{
		const val ACTION_MANAGE_OVERLAY_PERMISSION = 1
	}

}