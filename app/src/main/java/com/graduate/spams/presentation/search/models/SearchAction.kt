package com.graduate.spams.presentation.search.models

sealed class SearchAction {
	object BackPressed : SearchAction()
	object ShowNotify : SearchAction()
	object Nothing : SearchAction()
}