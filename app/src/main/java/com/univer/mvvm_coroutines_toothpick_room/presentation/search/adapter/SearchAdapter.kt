package com.univer.mvvm_coroutines_toothpick_room.presentation.search.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.domain.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.databinding.ItemHistoryBinding
import javax.inject.Inject

class SearchAdapter @Inject constructor() : ListDelegationAdapter<List<PhoneNumber>>(
	searchAdapterDelegate()
)

fun searchAdapterDelegate() = adapterDelegateViewBinding<PhoneNumber, PhoneNumber, ItemHistoryBinding>(
	{ layoutInflater, root -> ItemHistoryBinding.inflate(layoutInflater, root, false) }
) {
	bind {
		binding.apply {
			phoneNumber.text = item.number
		}
	}
}