package com.graduate.spams.presentation.detail

import com.graduate.spams.data.number.domain.PhoneNumber
import com.graduate.spams.model.detail.repository.DetailRepository
import javax.inject.Inject

class DetailInteractorImpl @Inject constructor(
	private val detailRepository: DetailRepository
): DetailInteractor {
	override suspend fun searchNumber(number: String) = detailRepository.searchNumber(number)
}