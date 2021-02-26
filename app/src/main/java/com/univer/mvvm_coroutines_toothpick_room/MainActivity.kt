package com.univer.mvvm_coroutines_toothpick_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.univer.mvvm_coroutines_toothpick_room.di.ActivityScope
import com.univer.mvvm_coroutines_toothpick_room.di.AppScope
import com.univer.mvvm_coroutines_toothpick_room.di.Scopes
import com.univer.mvvm_coroutines_toothpick_room.di.utils.ToothpickViewModelFactory
import com.univer.mvvm_coroutines_toothpick_room.extensions.subscribe
import com.univer.mvvm_coroutines_toothpick_room.models.AppAction
import com.univer.mvvm_coroutines_toothpick_room.models.AppViewState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import toothpick.Toothpick
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.installViewModelBinding

//TODO: Rename activity
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        initAppScope()
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            subscribe(mainViewModel.viewStates(), ::renderViewState)
            subscribe(mainViewModel.viewActions(), ::renderAction)
        }

        mainViewModel.test()
    }

    private fun initAppScope(){
        Toothpick.openScope(Scopes.APP_SCOPE)
            .openSubScope(Scopes.ACTIVITY_SCOPE){
                it.installViewModelBinding<MainViewModel>(
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

    }
}
