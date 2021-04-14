package com.graduate.spams.presentation.manage

import kotlinx.coroutines.flow.Flow

interface ManageInteractor {
	fun getUid(): Flow<String>
}