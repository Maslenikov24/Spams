package com.univer.mvvm_coroutines_toothpick_room.core

interface Mapper<FROM, TO> {
	fun map(from: FROM): TO
}