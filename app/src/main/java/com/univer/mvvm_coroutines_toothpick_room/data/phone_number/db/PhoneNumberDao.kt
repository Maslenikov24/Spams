package com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhoneNumberDao {

	@Query("Select * from NUMBER")
	suspend fun getHistory() : List<PhoneNumberEntity> //TODO: add stateFlow

	@Query("delete from NUMBER")
	suspend fun deleteAll()

	@Insert
	suspend fun insert(number: PhoneNumberEntity)

	@Delete
	suspend fun delete(number: PhoneNumberEntity)
}