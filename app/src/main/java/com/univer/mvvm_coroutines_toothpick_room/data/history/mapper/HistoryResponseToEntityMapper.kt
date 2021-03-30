package com.univer.mvvm_coroutines_toothpick_room.data.history.mapper

import com.univer.mvvm_coroutines_toothpick_room.core.Mapper
import com.univer.mvvm_coroutines_toothpick_room.data.history.db.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.number.net.PhoneNumberResponse
import java.util.*
import javax.inject.Inject

class HistoryResponseToEntityMapper @Inject constructor(

) : Mapper<PhoneNumberResponse, HistoryEntity> {
	override fun map(from: PhoneNumberResponse) = HistoryEntity(
		number = from.number,
		rating = from.rating,
		date = Date().time
	)
}