package com.graduate.spams.data.contact.domain

sealed class Contact {

	data class ContactInfo(
		val id: Long,
		val number: String,
		val name: String?,
		val image: String? = null,
		val type: Int = 0,
		val date: Long = 0,
		var count: Int = 1
	) : Contact()

	data class ContactDate(
		val date: Long
	) : Contact()
}