package com.univer.mvvm_coroutines_toothpick_room.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.univer.mvvm_coroutines_toothpick_room.R
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.log
import com.univer.mvvm_coroutines_toothpick_room.di.Scopes
import com.univer.mvvm_coroutines_toothpick_room.di.utils.ToothpickViewModelFactory
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.subscribe
import com.univer.mvvm_coroutines_toothpick_room.core.extensions.toast
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppEvent
import com.univer.mvvm_coroutines_toothpick_room.entry.models.AppViewState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.filterNotNull
import toothpick.Toothpick
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.installViewModelBinding

//TODO: Rename activity
class AppActivity : AppCompatActivity(R.layout.activity_main) {

    private val appViewModel by inject<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        initAppScope()
        super.onCreate(savedInstanceState)

        subscribe(appViewModel.viewStates(), ::renderViewState)
        subscribe(appViewModel.viewActions(), ::renderAction)

        appViewModel.obtainEvent(AppEvent.AppFirstStartEvent)

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

    private fun renderViewState(appState: AppViewState){
        when (appState){
            is AppViewState.AppErrorState -> {
                testTextView.text = "Wow"
            }
            is AppViewState.AppFirstStartState -> {
                /* nothing */
            }
            is AppViewState.TestState -> {
                testTextView.text = appState.text
            }
        }
    }

    private fun renderAction(appAction: AppAction){
        when (appAction){
            is AppAction.AppLogAction -> {
                toast("Success")
                //log("Success123")
            }
        }
    }
}
