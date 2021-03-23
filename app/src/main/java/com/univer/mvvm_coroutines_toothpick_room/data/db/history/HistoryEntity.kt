package com.univer.mvvm_coroutines_toothpick_room.data.db.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryEntity(
	@PrimaryKey
	@ColumnInfo(name = "number")
	var number: String
)
