package com.graduate.spams.data.history.mapper

import com.graduate.spams.core.Mapper
import com.graduate.spams.data.history.db.HistoryEntity
import com.graduate.spams.data.history.domain.HistoryNumber
import com.graduate.spams.data.number.net.PhoneNumberResponse
import java.util.*
import javax.inject.Inject

class HistoryEntityToDomainMapper @Inject constructor(

) : Mapper<HistoryEntity, HistoryNumber> {
	override fun map(from: HistoryEntity) = HistoryNumber(
		id = from.id,
		number = from.number,
		rating = from.rating,
		date = Date().time
	)
}