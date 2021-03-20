package com.univer.mvvm_coroutines_toothpick_room.di.module

import android.content.Context
import androidx.room.Room
import com.univer.mvvm_coroutines_toothpick_room.BuildConfig
import com.univer.mvvm_coroutines_toothpick_room.data.phone_number.db.PhoneNumberDao
import com.univer.mvvm_coroutines_toothpick_room.model.common.Database
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun roomModule(context: Context) = module {

	val DEFAULT_DATABASE_NAME = "database"

	val db = Room.databaseBuilder(context, Database::class.java, DEFAULT_DATABASE_NAME)
		.apply {
			if (BuildConfig.DEBUG){
				fallbackToDestructiveMigration()
			}
		}.build()

	bind<Database>().toInstance(db)
	bind<PhoneNumberDao>().toInstance(db.phoneNumberDao())
}