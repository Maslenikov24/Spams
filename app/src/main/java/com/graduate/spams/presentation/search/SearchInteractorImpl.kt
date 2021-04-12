package com.graduate.spams.presentation.search

import com.graduate.spams.model.search.repository.SearchRepository
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