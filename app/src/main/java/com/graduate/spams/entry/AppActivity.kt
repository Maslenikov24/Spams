package com.graduate.spams.entry

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.graduate.spams.R
import com.graduate.spams.core.AppNavigator
import com.graduate.spams.di.Scopes
import com.graduate.spams.di.utils.ToothpickViewModelFactory
import com.graduate.spams.core.extensions.subscribe
import com.graduate.spams.core.extensions.toast
import com.graduate.spams.core.presentation.BaseFragment
import com.graduate.spams.entry.models.AppAction
import com.graduate.spams.entry.models.AppEvent
import com.graduate.spams.entry.models.AppViewState
import com.graduate.spams.model.preferences.app.UiMode
import timber.log.Timber
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.lifecycle.closeOnDestroy
import toothpick.smoothie.viewmodel.installViewModelBinding

class AppActivity : AppCompatActivity(R.layout.activity_main) {

    private val roleManager by lazy { getSystemService(RoleManager::class.java) }

    private val viewModel by inject<AppViewModel>()

    private val navigator: Navigator = object : AppNavigator(this, R.id.container, supportFragmentManager){
        override fun applyCommand(command: Command) {
            super.applyCommand(command)
        }
    }

    private val intentHandler by lazy { IntentHandler() }

    private val currentFragment: BaseFragment<*>?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment<*>

    private fun initAppScope(){
        KTP.openScope(Scopes.APP_SCOPE)
            .openSubScope(Scopes.ACTIVITY_SCOPE){
                it.installViewModelBinding<AppViewModel>(
                    this,
                    ToothpickViewModelFactory(Scopes.ACTIVITY_SCOPE)
                )
            }.closeOnDestroy(this)
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initAppScope()
        Timber.tag("AppLog").d("Activity onCreate()")
        super.onCreate(savedInstanceState)
        Timber.tag("AppLog").d("savedInstanceState: ${savedInstanceState.toString()}")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        if (savedInstanceState == null) {
            subscribe(viewModel.viewStates(), ::renderViewState)
            subscribe(viewModel.viewActions(), ::renderAction)
            viewModel.obtainEvent(AppEvent.FirstStart)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                initService()
            }
        }
        intentHandler.handleIntent(intent)
        window?.setBackgroundDrawableResource(R.color.background)

    }

    private fun setUiMode(uiMode: UiMode){
        when (uiMode){
            UiMode.DAY -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            UiMode.NIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun renderViewState(viewState: AppViewState){
        when (viewState){
            is AppViewState.FirstStart -> {
                /* nothing */
            }
            is AppViewState.SetUiMode -> {
                setUiMode(viewState.uiMode)
            }
        }
    }

    private fun renderAction(viewAction: AppAction){
        when (viewAction){
            is AppAction.Log -> {
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

    private val customContract = registerForActivityResult(Contract()){ result ->

    }

    private fun initService(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when {
                roleManager.isRoleHeld(RoleManager.ROLE_CALL_SCREENING) ->
                    Timber.tag("AppLog").d("got role")
                roleManager.isRoleAvailable(RoleManager.ROLE_CALL_SCREENING) -> {
                    Timber.tag("AppLog").d("cannot hold role")
                    customContract.launch(REQUEST_CALLER_ID_APP)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    inner class Contract : ActivityResultContract<Int, TransactionResult>(){
        override fun createIntent(context: Context, input: Int?): Intent =
            roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)

        override fun parseResult(resultCode: Int, intent: Intent?) = TransactionResult(
            success = resultCode == REQUEST_CALLER_ID_APP
        )

    }

    data class TransactionResult(val success: Boolean)

    companion object {
        private const val REQUEST_CALLER_ID_APP = 1
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intentHandler.handleIntent(intent, true)
    }

    private inner class IntentHandler{
        fun handleIntent(intent: Intent?, isOpenInside: Boolean = false){
            if (intent == null) return
            val uid = intent.getStringExtra("uid")
            Timber.tag("AppLog handleIntent").d(uid)
            val startEvent =
                when{
                    !uid.isNullOrEmpty() -> StartEvent.AcceptParent(uid)
                    else -> StartEvent.Empty
                }
            viewModel.obtainEvent(AppEvent.HandleStartEvent(startEvent, isOpenInside))
        }
    }
}
