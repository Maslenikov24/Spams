package com.univer.mvvm_coroutines_toothpick_room.data.number.mapper

import com.univer.mvvm_coroutines_toothpick_room.core.Mapper
import com.univer.mvvm_coroutines_toothpick_room.data.number.domain.PhoneNumber
import com.univer.mvvm_coroutines_toothpick_room.data.number.net.PhoneNumberResponse
import javax.inject.Inject

class PhoneNumberResponseToDomainMapper @Inject constructor(
) : Mapper<PhoneNumberResponse, PhoneNumber> {

	override fun map(from: PhoneNumberResponse) = PhoneNumber(
		id = from.id,
		number = from.number,
		rating = from.rating
	)
}