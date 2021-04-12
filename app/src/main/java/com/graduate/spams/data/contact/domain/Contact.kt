package com.graduate.spams.data.contact.domain

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