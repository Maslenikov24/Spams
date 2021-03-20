package com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number")
data class PhoneNumberEntity(
	@PrimaryKey
	@ColumnInfo(name = "number")
	var number: String
)
