package com.graduate.spams.model.manage

import com.graduate.spams.di.IO
import com.graduate.spams.model.preferences.app.AppPreferenceStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ManageRepositoryImpl @Inject constructor(
	private val appPreferenceStorage: AppPreferenceStorage,
	@IO private val ioDispatcher: CoroutineDispatcher
) : ManageRepository {

	override fun getUid(): Flow<String> = appPreferenceStorage.uid.flowOn(ioDispatcher)
}