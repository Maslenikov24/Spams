package com.univer.mvvm_coroutines_toothpick_room.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.univer.mvvm_coroutines_toothpick_room.R
import com.univer.mvvm_coroutines_toothpick_room.di.Scopes
import com.univer.mvvm_coroutines_toothpick_room.di.utils.ToothpickViewModelFactory
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.subscribe
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toast
import com.univer.mvvm_coroutines_toothpick_room.core.presentation.BaseFragment
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppEvent
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppViewState
import toothpick.Toothpick
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.installViewModelBinding

class AppActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by inject<AppViewModel>()

    private val navigator: Navigator = object : AppNavigator(this, R.id.container/*, supportFragmentManager*/){
        override fun applyCommand(command: Command) {
            super.applyCommand(command)
        }
    }

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        initAppScope()
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        subscribe(viewModel.viewStates(), ::renderViewState)
        subscribe(viewModel.viewActions(), ::renderAction)

        if (savedInstanceState == null){
            viewModel.obtainEvent(AppEvent.AppFirstStartEvent)
        }

    }

    private fun initAppScope(){
        Toothpick.openScope(Scopes.APP_SCOPE)
            .openSubScope(Scopes.ACTIVITY_SCOPE){
                it.installViewModelBinding<AppViewModel>(
                    this,
                    ToothpickViewModelFactory(Scopes.ACTIVITY_SCOPE)
                )
            }.closeOnDestroy(this)
            .inject(this)
    }

    private fun renderViewState(appViewState: AppViewState){
        when (appViewState){
            is AppViewState.AppErrorState -> {
                /* nothing */
            }
            is AppViewState.AppFirstStartState -> {
                /* nothing */
            }
            is AppViewState.TestState -> {
                /* nothing */
            }
        }
    }

    private fun renderAction(appAction: AppAction){
        when (appAction){
            is AppAction.AppLogAction -> {
                toast("Success")
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        viewModel.addNavigator(navigator)
    }

    override fun onPause() {
        viewModel.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}
