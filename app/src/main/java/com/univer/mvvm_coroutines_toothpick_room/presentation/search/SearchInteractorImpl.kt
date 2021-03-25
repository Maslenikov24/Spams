package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.univer.mvvm_coroutines_toothpick_room.model.search.repository.SearchRepository
import timber.log.Timber
import java.net.ConnectException
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(
	private val searchRepository: SearchRepository
) : SearchInteractor {

	override suspend fun getNumber(number: String) = searchRepository.getNumber(number)

	override suspend fun getHistory() = searchRepository.getHistory()
}