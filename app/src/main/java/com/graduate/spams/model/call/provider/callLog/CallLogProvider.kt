package com.graduate.spams.model.call.provider.callLog

import com.graduate.spams.data.contact.domain.Contact

interface CallLogProvider {
	fun getPhoneBook() : List<Contact>
}