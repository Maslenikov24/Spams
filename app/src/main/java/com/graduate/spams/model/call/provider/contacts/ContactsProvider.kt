package com.graduate.spams.model.call.provider.contacts

import com.graduate.spams.data.contact.domain.Contact

interface ContactsProvider {
	fun getContacts() : List<Contact.ContactInfo>
}