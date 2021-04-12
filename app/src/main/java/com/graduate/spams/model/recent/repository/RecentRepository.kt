package com.graduate.spams.model.recent.repository

import com.graduate.spams.data.contact.domain.Contact
import kotlinx.coroutines.flow.Flow

interface RecentRepository {
	fun getPhoneBook(): Flow<List<Contact>>
}