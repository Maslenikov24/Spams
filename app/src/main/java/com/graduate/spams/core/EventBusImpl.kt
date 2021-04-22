package com.graduate.spams.core

import javax.inject.Inject

class PermissionsListener @Inject constructor(): EventBus<Boolean>()