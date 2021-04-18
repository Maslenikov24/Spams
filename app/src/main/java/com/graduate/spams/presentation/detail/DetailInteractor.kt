package com.graduate.spams.presentation.detail

import com.graduate.spams.data.number.domain.PhoneNumber

interface DetailInteractor {
	suspend fun searchNumber(number: String): PhoneNumber?
}