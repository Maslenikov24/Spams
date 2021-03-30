package com.univer.mvvm_coroutines_toothpick_room.data.db.history

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

	@Query("Select * from History order by date desc")
	fun getHistory() : Flow<List<HistoryEntity>>

	@Insert
	suspend fun insert(number: HistoryEntity)

	@Query("Delete from History where id = :id")
	suspend fun delete(id: Long)

	@Query("Delete from History")
	suspend fun deleteAll()
}
