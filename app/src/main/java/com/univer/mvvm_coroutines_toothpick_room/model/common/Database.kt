package com.univer.mvvm_coroutines_toothpick_room.model.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db.PhoneNumberDao
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db.PhoneNumberEntity

@Database(
	entities = [
		PhoneNumberEntity::class
	],
	version = 1
)

abstract class Database: RoomDatabase() {

	abstract fun phoneNumberDao(): PhoneNumberDao
}