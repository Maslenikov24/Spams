package com.univer.mvvm_coroutines_toothpick_room.presentation.recent.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.ContactWrapper
import com.univer.mvvm_coroutines_toothpick_room.databinding.ItemContactBinding
import javax.inject.Inject

class RecentAdapter @Inject constructor() : ListDelegationAdapter<List<ContactWrapper>>(

)

fun recentAdapterDelegate() = adapterDelegateViewBinding<ContactWrapper, ContactWrapper, ItemContactBinding>(
	{ layoutInflater, root ->  ItemContactBinding.inflate(layoutInflater, root, false)}
) {
	bind {
		binding.apply {

		}
	}
}

