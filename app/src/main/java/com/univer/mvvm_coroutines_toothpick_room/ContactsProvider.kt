package com.univer.mvvm_coroutines_toothpick_room

import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.Contact
import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.ContactWrapper

interface ContactsProvider {
	private fun getCalls() {}
	private fun wrapCalls(calls: List<Contact>) {}
	fun getPhoneBook() : List<ContactWrapper>
}