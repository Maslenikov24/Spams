package com.univer.mvvm_coroutines_toothpick_room.data.domain.contact

data class Contact(
	val id: Long,
	val number: String,
	val name: String?,
	val image: String?,
	val type: Int,
	val date: Long,
	var count: Int = 1
)