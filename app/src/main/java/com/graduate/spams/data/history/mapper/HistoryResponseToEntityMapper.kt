package com.graduate.spams.data.history.mapper

import com.graduate.spams.core.Mapper
import com.graduate.spams.data.history.db.HistoryEntity
import com.graduate.spams.data.number.net.PhoneNumberResponse
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