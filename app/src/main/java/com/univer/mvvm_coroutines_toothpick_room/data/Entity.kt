package com.univer.mvvm_coroutines_toothpick_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Entity(
	@PrimaryKey
	var id: Long
)
