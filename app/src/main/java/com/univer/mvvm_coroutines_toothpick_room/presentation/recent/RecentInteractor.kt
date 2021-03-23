package com.univer.mvvm_coroutines_toothpick_room.presentation.recent

import com.univer.mvvm_coroutines_toothpick_room.data.domain.contact.ContactWrapper
import kotlinx.coroutines.flow.Flow

interface RecentInteractor {
	fun getPhoneBook(): Flow<List<ContactWrapper>>
}