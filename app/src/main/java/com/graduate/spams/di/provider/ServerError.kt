package com.graduate.spams.di.provider

import java.lang.RuntimeException

class ServerError(val errorCode: Int): RuntimeException()