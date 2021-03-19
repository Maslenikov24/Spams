package com.univer.mvvm_coroutines_toothpick_room.data.number.mapper

import com.univer.mvvm_coroutines_toothpick_room.core.Mapper
import com.univer.mvvm_coroutines_toothpick_room.data.number.net.NumberResponse

class NumberResponseToDomainMapper : Mapper<Number, NumberResponse> {

	override fun map(from: Number): NumberResponse {
		return from as NumberResponse
	}
}