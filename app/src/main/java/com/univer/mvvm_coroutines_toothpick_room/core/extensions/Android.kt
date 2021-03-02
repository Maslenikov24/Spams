package com.univer.mvvm_coroutines_toothpick_room.core.extensions

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"