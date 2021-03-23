package com.univer.mvvm_coroutines_toothpick_room.data.db.history

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

	@Query("Select * from History")
	suspend fun getHistory() : List<HistoryEntity> //TODO: add stateFlow

	@Query("delete from History")
	suspend fun deleteAll()

	@Insert
	suspend fun insert(number: HistoryEntity)

	@Delete
	suspend fun delete(number: HistoryEntity)
}