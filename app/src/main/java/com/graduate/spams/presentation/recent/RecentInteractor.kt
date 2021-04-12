package com.graduate.spams.presentation.recent

import com.graduate.spams.data.contact.domain.Contact
import kotlinx.coroutines.flow.Flow

interface RecentInteractor {
	fun getPhoneBook(): Flow<List<Contact>>
}