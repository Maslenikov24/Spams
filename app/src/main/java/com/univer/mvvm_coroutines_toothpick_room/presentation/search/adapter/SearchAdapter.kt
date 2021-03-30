package com.univer.mvvm_coroutines_toothpick_room.presentation.search.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.univer.mvvm_coroutines_toothpick_room.R
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toDateWithNormalization
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toTime
import com.univer.mvvm_coroutines_toothpick_room.data.history.domain.HistoryNumber
import com.univer.mvvm_coroutines_toothpick_room.databinding.ItemHistoryBinding
import com.univer.mvvm_coroutines_toothpick_room.di.Adapter
import javax.inject.Inject

class SearchAdapter @Inject constructor(
	@Adapter private val detailClickListener: (String) -> Unit,
	private val deleteClickListener: (Long) -> Unit
) : ListDelegationAdapter<List<HistoryNumber>>(
	searchAdapterDelegate(detailClickListener, deleteClickListener)
)

fun searchAdapterDelegate(
	detailClickListener: (String) -> Unit,
	removeClickListener: (Long) -> Unit
) = adapterDelegateViewBinding<HistoryNumber, HistoryNumber, ItemHistoryBinding>(
	{ layoutInflater, root -> ItemHistoryBinding.inflate(layoutInflater, root, false) }
) {
	bind {
		binding.apply {
			phoneNumber.text = item.number
			val time = item.date.toDateWithNormalization() + " в " + item.date.toTime()
			date.text = time

			historyItem.setOnClickListener {
				detailClickListener.invoke(item.number)
			}
			deleteItem.setOnClickListener {
				removeClickListener.invoke(item.id)
			}

			when (item.rating){
				0 -> {
					ratingIcon.setImageResource(R.drawable.ic_info)
				}
				5 -> {
					ratingIcon.setImageResource(R.drawable.ic_warning)
				}
				10 -> {
					ratingIcon.setImageResource(R.drawable.ic_error)
				}
			}
		}
	}
}