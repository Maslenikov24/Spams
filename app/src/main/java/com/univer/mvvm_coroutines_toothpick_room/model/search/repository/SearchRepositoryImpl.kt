package com.univer.mvvm_coroutines_toothpick_room.model.search.repository

import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db.PhoneNumberDao
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db.PhoneNumberEntity
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.domain.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(

	private val phoneNumberDao: PhoneNumberDao //TODO: refactor to history
): SearchRepository {

	override suspend fun getNumber(number: String) {
		//searchService.getNumber(number)
		saveToHistory(number) //TODO: save Number
	}

	override suspend fun getHistory() : List<PhoneNumber> {
		val result = phoneNumberDao.getHistory()
		return result.map { PhoneNumber("", it.number, 0) }
	}

	override suspend fun saveToHistory(number: String) {
		phoneNumberDao.insert(PhoneNumberEntity(number)) //TODO: delete from import PhoneNumberEntity
	}

	override suspend fun deleteFromHistory(number: String) {

	}


}