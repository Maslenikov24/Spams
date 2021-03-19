package com.univer.mvvm_coroutines_toothpick_room.model.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.univer.mvvm_coroutines_toothpick_room.data.number.db.NumberDao
import com.univer.mvvm_coroutines_toothpick_room.data.number.db.NumberEntity

@Database(
	entities = [
		NumberEntity::class
	],
	version = 1
)

abstract class Database: RoomDatabase() {

	abstract fun numberDao(): NumberDao
}