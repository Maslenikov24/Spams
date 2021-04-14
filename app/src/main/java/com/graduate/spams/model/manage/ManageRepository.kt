package com.graduate.spams.model.manage

import kotlinx.coroutines.flow.Flow

interface ManageRepository {
	fun getUid(): Flow<String>
}