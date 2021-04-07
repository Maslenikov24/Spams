package com.univer.mvvm_coroutines_toothpick_room.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.objectScopeName
import com.univer.mvvm_coroutines_toothpick_room.di.Scopes
import com.univer.mvvm_coroutines_toothpick_room.di.utils.ToothpickViewModelFactory
import timber.log.Timber
import toothpick.Scope
import toothpick.ktp.KTP
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB: ViewBinding> : Fragment() {

	protected open val parentScopeName: String by lazy {
		(parentFragment as? BaseFragment<*>)?.fragmentScopeName ?: Scopes.ACTIVITY_SCOPE
	}

	private lateinit var fragmentScopeName: String

	lateinit var scope: Scope
		private set

	private var instanceStateSaved: Boolean = false

	protected val binging by viewBinding(getViewBindingClass(), CreateMethod.INFLATE)

	private fun getViewBindingClass(): Class<VB> =
		(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<VB>

	protected open fun installModules(scope: Scope) {}

	private fun restoreScope(savedInstanceState: Bundle?){
		fragmentScopeName = savedInstanceState?.getString(STATE_SCOPE_NAME) ?: objectScopeName()
		scope = if (KTP.isScopeOpen(fragmentScopeName)){
			KTP.openScope(fragmentScopeName)
		} else {
			val openScopes = KTP.openScope(parentScopeName).openSubScope(fragmentScopeName)
			installModules(openScopes)
			openScopes
		}.closeOnDestroy(this)
		Timber.tag("AppLog").v("${KTP.openScope(Scopes.APP_SCOPE)}")
		scope.inject(this)

		//Toothpick.inject(this, scope)
		/*scope.inject(this)
        if (!scopeOpen) {
            scopeRestored(scope)
        }*/
	}

	open fun scopeRestored(scope: Scope) {}

	override fun onCreate(savedInstanceState: Bundle?) {
		restoreScope(savedInstanceState)
		super.onCreate(savedInstanceState)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		instanceStateSaved = true

		outState.putString(STATE_SCOPE_NAME, fragmentScopeName)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View = binging.root

	open fun onBackPressed() {}

	//TODO: understand it
	protected inline fun <reified T : ViewModel> Scope.installViewModel() {
		installViewModelBinding<T>(this@BaseFragment, ToothpickViewModelFactory(name))
		closeOnViewModelCleared(this@BaseFragment)
	}

	companion object {
		private const val STATE_SCOPE_NAME = "state_scope_name"
	}
}