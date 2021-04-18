package com.graduate.spams.model.detail.repository

import com.graduate.spams.data.number.domain.PhoneNumber

interface DetailRepository {
	suspend fun searchNumber(number: String) : PhoneNumber?
}