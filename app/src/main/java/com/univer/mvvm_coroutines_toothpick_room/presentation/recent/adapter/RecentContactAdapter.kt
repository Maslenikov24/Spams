package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.adapter

import android.provider.CallLog
import coil.load
import coil.transform.CircleCropTransformation
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.univer.mvvm_coroutines_toothpick_room.R
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toTime
import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.Contact
import com.univer.mvvm_coroutines_toothpick_room.databinding.ItemContactBinding
import javax.inject.Inject

class RecentContactAdapter @Inject constructor() : ListDelegationAdapter<List<Contact>>(
	recentContactAdapterDelegate()
)

fun recentContactAdapterDelegate() = adapterDelegateViewBinding<Contact, Contact, ItemContactBinding>(
	{ layoutInflater, root ->  ItemContactBinding.inflate(layoutInflater, root, false)}
) {
	bind {
		binding.apply {
			phoneNumber.text =if (item.name != null) item.name else item.number
			if (item.count > 1) phoneNumber.text = "${phoneNumber.text} (${item.count})"
			contactTime.text = item.date.toTime()
			when (item.type){
				CallLog.Calls.INCOMING_TYPE -> { contactType.load(R.drawable.ic_incoming) }
				CallLog.Calls.OUTGOING_TYPE -> {contactType.load(R.drawable.ic_outgoing) }
				CallLog.Calls.MISSED_TYPE -> {contactType.load(R.drawable.ic_missed) }
				CallLog.Calls.REJECTED_TYPE -> {contactType.load(R.drawable.ic_canceled) }
				CallLog.Calls.ANSWERED_EXTERNALLY_TYPE -> { /*nothing*/ }
				CallLog.Calls.VOICEMAIL_TYPE -> { /*nothing*/ }
				CallLog.Calls.BLOCKED_TYPE -> { /*nothing*/ }
			}
		}
	}
}

