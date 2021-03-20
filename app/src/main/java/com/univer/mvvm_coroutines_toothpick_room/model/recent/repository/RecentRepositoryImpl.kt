package com.univer.mvvm_coroutines_toothpick_room.model.recent.repository

import android.content.Context
import com.univer.mvvm_coroutines_toothpick_room.ContactsProvider
import com.univer.mvvm_coroutines_toothpick_room.ContactsProviderImpl
import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.ContactWrapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
	private val context: Context
) : RecentRepository {

	override fun getPhoneBook() = flow {
		val contactsProvider = ContactsProviderImpl(context)
		emit(contactsProvider.getPhoneBook())
	}

}