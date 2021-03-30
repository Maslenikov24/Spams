package com.univer.mvvm_coroutines_toothpick_room.presentation.recent

import com.univer.mvvm_coroutines_toothpick_room.data.contact.domain.Contact
import com.univer.mvvm_coroutines_toothpick_room.model.recent.repository.RecentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentInteractorImpl @Inject constructor(
	private val recentRepository: RecentRepository
) : RecentInteractor {

	override fun getPhoneBook(): Flow<List<Contact>> = recentRepository.getPhoneBook()

}