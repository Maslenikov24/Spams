package com.univer.mvvm_coroutines_toothpick_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.univer.mvvm_coroutines_toothpick_room.di.ActivityScope
import com.univer.mvvm_coroutines_toothpick_room.di.AppScope
import com.univer.mvvm_coroutines_toothpick_room.di.utils.ToothpickViewModelFactory
import com.univer.mvvm_coroutines_toothpick_room.extensions.subscribe
import kotlinx.android.synthetic.main.activity_main.*
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
        subscribe(mainViewModel.state, ::renderState)

        //testing
        mainViewModel.testAction()
    }

    private fun initAppScope(){
        Toothpick.openScope(AppScope::class.java)
            .openSubScope(ActivityScope::class.java){
                it.installViewModelBinding<MainViewModel>(
                    this,
                    ToothpickViewModelFactory(ActivityScope::class.java)
                )
            }.closeOnDestroy(this)
            .inject(this)
    }

    private fun renderState(appState: AppState){
        //UI.isVisible() = appState.isError
        testTextView.text = appState.testString
    }
}
