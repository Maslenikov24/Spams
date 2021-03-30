package com.univer.mvvm_coroutines_toothpick_room.data.history.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryEntity(
	@ColumnInfo(name = "number")
	var number: String,
	@ColumnInfo(name = "rating")
	var rating: Int,
	@ColumnInfo(name = "date")
	var date: Long
){
	@PrimaryKey (autoGenerate = true)
	var id: Long = 0
}
