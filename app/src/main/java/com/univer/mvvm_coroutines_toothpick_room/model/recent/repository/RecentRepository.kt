package com.univer.mvvm_coroutines_toothpick_room.model.recent.repository

import com.univer.mvvm_coroutines_toothpick_room.data.contact.domain.Contact
import kotlinx.coroutines.flow.Flow

interface RecentRepository {
	fun getPhoneBook(): Flow<List<Contact>>
}