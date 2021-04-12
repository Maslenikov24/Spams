package com.graduate.spams.presentation.recent

import com.graduate.spams.data.contact.domain.Contact
import com.graduate.spams.model.recent.repository.RecentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentInteractorImpl @Inject constructor(
	private val recentRepository: RecentRepository
) : RecentInteractor {

	override fun getPhoneBook(): Flow<List<Contact>> = recentRepository.getPhoneBook()

}