package com.univer.mvvm_coroutines_toothpick_room.model.call

import com.univer.mvvm_coroutines_toothpick_room.data.contact.domain.Contact

interface ContactsProvider {
	fun getPhoneBook() : List<Contact>
}