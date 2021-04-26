package com.graduate.spams.model.call.provider

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.graduate.spams.R
import com.graduate.spams.core.CallListener
import com.graduate.spams.core.CoroutinesHelper
import com.graduate.spams.data.number.domain.PhoneNumber
import com.graduate.spams.model.call.provider.contacts.ContactsProviderImpl
import com.graduate.spams.presentation.detail.DetailInteractor
import kotlinx.coroutines.flow.collect
import android.widget.LinearLayout
import com.graduate.spams.presentation.common.shape.contentWindowShape
import com.graduate.spams.presentation.common.shape.footerWindowShape
import javax.inject.Inject


//TODO: inject after getting permission
class IncomingWindowProviderImpl @Inject constructor(
	private val context: Context,
	private val interactor: DetailInteractor,
	private val callListener: CallListener
): IncomingWindowProvider, CoroutinesHelper() {

	private var isOpen = true

	//TODO: viewBinding
	override fun openWindow(phoneNumber: String){
		val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		val windowLayout = layoutInflater.inflate(R.layout.layout_incoming_window, null)
		if (!ContactsProviderImpl(context).checkingNumberExistence(phoneNumber)) {
			Toast.makeText(context, "Входящий от $phoneNumber", Toast.LENGTH_SHORT).show()
			val type = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
				@Suppress("DEPRECATION")
				WindowManager.LayoutParams.TYPE_PHONE
			} else {
				WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
			}

			val params = WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				type,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT
			)
			params.gravity = Gravity.TOP

			//TODO: viewBinding
			val rootWindow = windowLayout.findViewById<ConstraintLayout>(R.id.rootWindow)
			val footer = windowLayout.findViewById<LinearLayout>(R.id.footer)
			val buttonClose = windowLayout.findViewById<ImageView>(R.id.close)
			val windowNumber = windowLayout.findViewById<TextView>(R.id.number)
			val windowWarning = windowLayout.findViewById<TextView>(R.id.warning)
			ui {
				//val result = interactor.searchNumber(phoneNumber)
				val result = PhoneNumber("", phoneNumber, rating = 0)

				result?.let {
					windowNumber.text = result.number
					windowWarning.text = when (result.rating) {
						10 -> {
							val backgroundColor = context.getColor(R.color.colorError)
							buttonClose.setColorFilter(context.getColor(R.color.white))
							rootWindow.background = contentWindowShape(backgroundColor)
							"Спам"
						}
						5 -> {
							val backgroundColor = context.getColor(R.color.colorAccent)
							buttonClose.setColorFilter(context.getColor(R.color.white))
							rootWindow.background = contentWindowShape(backgroundColor)
							buttonClose.setImageResource(R.drawable.ic_remove)
							"Возможно, спам"
						}
						0 -> {
							val backgroundColor = context.getColor(R.color.lightGray)
							rootWindow.background = contentWindowShape(backgroundColor)
							windowNumber.setTextColor(context.getColor(R.color.colorOnSecondary))
							windowWarning.setTextColor(context.getColor(R.color.colorOnSecondary))
							"Информация о номере отсутствует"
						}
						else -> ""
					}
				}
				footer.background = footerWindowShape(context)
				windowManager.addView(windowLayout, params)
				isOpen = true

				buttonClose.setOnClickListener {
					windowManager.removeView(windowLayout)
					isOpen = false
				}
				callListener.collect {
					if (isOpen) windowManager.removeView(windowLayout)
				}

			}
		}
	}

}