package com.univer.mvvm_coroutines_toothpick_room.data.mapper.history

import com.univer.mvvm_coroutines_toothpick_room.core.Mapper
import com.univer.mvvm_coroutines_toothpick_room.data.db.history.HistoryEntity
import com.univer.mvvm_coroutines_toothpick_room.data.net.number.PhoneNumberResponse
import java.text.SimpleDateFormat
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