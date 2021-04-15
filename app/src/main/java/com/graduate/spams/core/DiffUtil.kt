package com.graduate.spams.core

import androidx.recyclerview.widget.DiffUtil
import com.graduate.spams.data.history.domain.HistoryNumber

class SearchHistoryDiffUtil(
	private val newList: List<HistoryNumber>,
	private val oldList: List<HistoryNumber>
): DiffUtil.Callback() {

	override fun getOldListSize() = oldList.size

	override fun getNewListSize() = newList.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val newItem = newList[newItemPosition]
		val oldItem = oldList[oldItemPosition]
		return newItem.id == oldItem.id
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val newItem = newList[newItemPosition]
		val oldItem = oldList[oldItemPosition]
		return newItem.number == oldItem.number && newItem.date == oldItem.date
	}
}