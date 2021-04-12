package com.graduate.spams.model.call.provider

import com.graduate.spams.data.contact.domain.Contact

interface ContactsProvider {
	fun getPhoneBook() : List<Contact>
}