package com.graduate.spams.core.extensions

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"