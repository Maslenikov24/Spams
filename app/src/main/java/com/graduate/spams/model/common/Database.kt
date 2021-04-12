package com.graduate.spams.model.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.graduate.spams.data.history.db.HistoryDao
import com.graduate.spams.data.history.db.HistoryEntity

@Database(
	entities = [
		HistoryEntity::class
	],
	version = 2
)

abstract class Database: RoomDatabase() {

	abstract fun phoneNumberDao(): HistoryDao
}