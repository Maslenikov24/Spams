package com.univer.mvvm_coroutines_toothpick_room.di.provider

import java.lang.RuntimeException

class ServerError(val errorCode: Int): RuntimeException()