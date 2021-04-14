package com.graduate.spams.presentation.manage

import com.graduate.spams.model.manage.ManageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageInteractorImpl @Inject constructor(
	private val manageRepository: ManageRepository
) : ManageInteractor {

	override fun getUid(): Flow<String> = manageRepository.getUid()
}