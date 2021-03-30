package com.univer.mvvm_coroutines_toothpick_room.model.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.univer.mvvm_coroutines_toothpick_room.data.history.db.HistoryDao
import com.univer.mvvm_coroutines_toothpick_room.data.history.db.HistoryEntity

@Database(
	entities = [
		HistoryEntity::class
	],
	version = 2
)

abstract class Database: RoomDatabase() {

	abstract fun phoneNumberDao(): HistoryDao
}