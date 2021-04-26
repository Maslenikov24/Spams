package com.graduate.spams.model.recent.repository

import android.content.Context
import com.graduate.spams.model.call.provider.callLog.CallLogProviderImpl
import com.graduate.spams.di.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
	private val context: Context,
	@IO private val ioDispatcher: CoroutineDispatcher
) : RecentRepository {

	override fun getPhoneBook() = flow {
		val callLogProvider = CallLogProviderImpl(context)
		emit(callLogProvider.getPhoneBook())
	}.flowOn(ioDispatcher)

}