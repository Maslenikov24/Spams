package com.univer.mvvm_coroutines_toothpick_room

import com.univer.mvvm_coroutines_toothpick_room.data.domain.contact.Contact


interface ContactsProvider {
	private fun getCalls() {}
	private fun wrapCalls(data: List<Contact>) {}
	fun getPhoneBook() : List<Contact>
}