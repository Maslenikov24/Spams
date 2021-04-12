package com.graduate.spams.data.blacklist.domain

data class BlackList(
	val serverNumbers: List<Number>,
	val ownNumbers: List<Number> //Todo: логика сохранения в базу данных плохих номеров
)
