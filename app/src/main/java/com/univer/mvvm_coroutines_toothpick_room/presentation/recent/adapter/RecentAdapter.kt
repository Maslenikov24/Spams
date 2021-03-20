package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toDateWithNormalization
import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.ContactWrapper
import com.univer.mvvm_coroutines_toothpick_room.databinding.ItemContactBinding
import com.univer.mvvm_coroutines_toothpick_room.databinding.ItemWrappedContactBinding
import javax.inject.Inject

class RecentAdapter @Inject constructor(
	private val adapter: RecentContactAdapter
) : ListDelegationAdapter<List<ContactWrapper>>(
	recentAdapterDelegate(adapter)
)

fun recentAdapterDelegate(contactAdapter: RecentContactAdapter) = adapterDelegateViewBinding<ContactWrapper, ContactWrapper, ItemWrappedContactBinding>(
	{ layoutInflater, root ->  ItemWrappedContactBinding.inflate(layoutInflater, root, false)}
) {
	bind {
		binding.apply {
			date.text = item.date.toDateWithNormalization()
			recyclerView.apply {
				adapter = contactAdapter
				setHasFixedSize(true)
			}
		}

		contactAdapter.items = item.contacts
	}
}

