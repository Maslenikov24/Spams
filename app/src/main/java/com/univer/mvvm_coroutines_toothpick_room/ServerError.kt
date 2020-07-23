package com.univer.mvvm_coroutines_toothpick_room

import java.lang.RuntimeException

class ServerError(val errorCode: Int): RuntimeException()