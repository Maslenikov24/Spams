package com.graduate.spams.presentation.recent.adapter

import android.provider.CallLog
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.graduate.spams.R
import com.graduate.spams.core.extensions.toDateWithNormalization
import com.graduate.spams.core.extensions.toTime
import com.graduate.spams.data.contact.domain.Contact
import com.graduate.spams.databinding.ItemContactDateBinding
import com.graduate.spams.databinding.ItemContactInfoBinding
import javax.inject.Inject

class RecentAdapter @Inject constructor(
	detailClickListener: (String, String?) -> Unit
) : ListDelegationAdapter<List<Contact>>(
	contactDateAdapterDelegate(),
	contactInfoAdapterDelegate(detailClickListener)
) {

}

fun contactDateAdapterDelegate() = adapterDelegateViewBinding<Contact.ContactDate, Contact, ItemContactDateBinding>(
	{ layoutInflater, root ->  ItemContactDateBinding.inflate(layoutInflater, root, false)}
) {
	bind {
		binding.apply {
			date.text = item.date.toDateWithNormalization()
		}
	}
}

fun contactInfoAdapterDelegate(detailClickListener: (String, String?) -> Unit) =
	adapterDelegateViewBinding<Contact.ContactInfo, Contact, ItemContactInfoBinding>(
		{ layoutInflater, root -> ItemContactInfoBinding.inflate(layoutInflater, root, false) }
	) {
		bind {
			binding.apply {
				phoneNumber.text = if (item.name != null) item.name else item.number
				if (item.count > 1) phoneNumber.text = "${phoneNumber.text} (${item.count})"
				contactTime.text = item.date.toTime()
				contactLayout.setOnClickListener {
					detailClickListener.invoke(item.number, item.name)
				}
				when (item.type) {
					CallLog.Calls.INCOMING_TYPE -> {
						contactType.setImageResource(R.drawable.ic_call_incoming)
					}
					CallLog.Calls.OUTGOING_TYPE -> {
						contactType.setImageResource(R.drawable.ic_call_outgoing)
					}
					CallLog.Calls.MISSED_TYPE -> {
						contactType.setImageResource(R.drawable.ic_call_missed)
					}
					CallLog.Calls.REJECTED_TYPE -> {
						contactType.setImageResource(R.drawable.ic_call_canceled)
					}
					CallLog.Calls.ANSWERED_EXTERNALLY_TYPE -> { /*nothing*/
					}
					CallLog.Calls.VOICEMAIL_TYPE -> { /*nothing*/
					}
					CallLog.Calls.BLOCKED_TYPE -> { /*nothing*/
					}
				}
			}
		}
	}

