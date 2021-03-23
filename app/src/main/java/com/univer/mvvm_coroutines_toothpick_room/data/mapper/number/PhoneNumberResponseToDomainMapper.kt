package com.univer.mvvm_coroutines_toothpick_room.data.mapper.number

import com.univer.mvvm_coroutines_toothpick_room.core.Mapper
import com.univer.mvvm_coroutines_toothpick_room.data.net.number.PhoneNumberResponse

class PhoneNumberResponseToDomainMapper : Mapper<Number, PhoneNumberResponse> {

	override fun map(from: Number): PhoneNumberResponse {
		return from as PhoneNumberResponse
	}
}