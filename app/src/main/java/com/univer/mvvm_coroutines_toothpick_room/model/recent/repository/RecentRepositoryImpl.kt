package com.univer.mvvm_coroutines_toothpick_room.model.recent.repository

import android.content.Context
import com.univer.mvvm_coroutines_toothpick_room.ContactsProviderImpl
import com.univer.mvvm_coroutines_toothpick_room.di.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
	private val context: Context,
	@IO private val ioDispatcher: CoroutineDispatcher
) : RecentRepository {

	override fun getPhoneBook() = flow {
		val contactsProvider = ContactsProviderImpl(context)
		emit(contactsProvider.getPhoneBook())
	}.flowOn(ioDispatcher)

}