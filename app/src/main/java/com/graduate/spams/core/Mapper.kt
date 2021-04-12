package com.graduate.spams.core

interface Mapper<FROM, TO> {
	fun map(from: FROM): TO
}