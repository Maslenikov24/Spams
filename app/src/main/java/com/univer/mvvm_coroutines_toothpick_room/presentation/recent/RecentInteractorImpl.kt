package com.univer.mvvm_coroutines_toothpick_room.presentation.recent

import com.univer.mvvm_coroutines_toothpick_room.ContactsProvider
import com.univer.mvvm_coroutines_toothpick_room.data.contact.model.ContactWrapper
import com.univer.mvvm_coroutines_toothpick_room.model.recent.repository.RecentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentInteractorImpl @Inject constructor(
	private val recentRepository: RecentRepository
) : RecentInteractor {

	override fun getPhoneBook(): Flow<List<ContactWrapper>> = recentRepository.getPhoneBook()

}