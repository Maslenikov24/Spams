package com.univer.mvvm_coroutines_toothpick_room.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.univer.mvvm_coroutines_toothpick_room.data.Dao
import com.univer.mvvm_coroutines_toothpick_room.data.Entity

@Database(
	entities = [
		Entity::class
	],
	version = 1
)

abstract class Database: RoomDatabase() {

	abstract fun dao(): Dao
}