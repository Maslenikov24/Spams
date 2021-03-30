package com.univer.mvvm_coroutines_toothpick_room.presentation.search

import com.univer.mvvm_coroutines_toothpick_room.model.search.repository.SearchRepository
import timber.log.Timber
import java.net.ConnectException
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(
	private val searchRepository: SearchRepository
) : SearchInteractor {

	override suspend fun searchNumber(number: String) = searchRepository.searchNumber(number)

	override fun getHistory() = searchRepository.getHistory()

	override suspend fun deleteFromHistory(id: Long) = searchRepository.deleteFromHistory(id)

	override suspend fun deleteAllHistory() = searchRepository.deleteAllHistory()
}