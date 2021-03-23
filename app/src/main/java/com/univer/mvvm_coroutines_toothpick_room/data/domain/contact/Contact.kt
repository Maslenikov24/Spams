package com.univer.mvvm_coroutines_toothpick_room.data.domain.contact

sealed class Contact {

	data class ContactInfo(
		val id: Long,
		val number: String,
		val name: String?,
		val image: String?,
		val type: Int,
		val date: Long,
		var count: Int = 1
	) : Contact()

	data class ContactDate(
		val date: Long
	) : Contact()
}