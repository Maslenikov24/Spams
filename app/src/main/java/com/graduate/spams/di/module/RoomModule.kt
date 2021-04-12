package com.graduate.spams.di.module

import android.content.Context
import androidx.room.Room
import com.graduate.spams.BuildConfig
import com.graduate.spams.data.history.db.HistoryDao
import com.graduate.spams.model.common.Database
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

	// Dao
	bind<HistoryDao>().toInstance(db.phoneNumberDao())
}