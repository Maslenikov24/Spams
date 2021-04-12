package com.graduate.spams.data.number.mapper

import com.graduate.spams.core.Mapper
import com.graduate.spams.data.number.domain.PhoneNumber
import com.graduate.spams.data.number.net.PhoneNumberResponse
import javax.inject.Inject

class PhoneNumberResponseToDomainMapper @Inject constructor(
) : Mapper<PhoneNumberResponse, PhoneNumber> {

	override fun map(from: PhoneNumberResponse) = PhoneNumber(
		id = from.id,
		number = from.number,
		rating = from.rating
	)
}