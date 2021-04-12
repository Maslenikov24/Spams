package com.graduate.spams.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.fragment.app.*
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.AppScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.terrakok.cicerone.androidx.TransactionInfo
import com.github.terrakok.cicerone.androidx.TransactionInfo.Type.ADD
import com.github.terrakok.cicerone.androidx.TransactionInfo.Type.REPLACE
import com.graduate.spams.R

/**
 * Navigator implementation for launch fragments and activities.
 *
 * Feature [BackTo] works only for fragments.
 *
 * Recommendation: most useful for Single-Activity application.
 */
open class AppNavigator @JvmOverloads constructor(
	protected val activity: FragmentActivity,
	protected val containerId: Int,
	protected val fragmentManager: FragmentManager = activity.supportFragmentManager,
	protected val fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory
) : Navigator {

	protected val localStackCopy = mutableListOf<TransactionInfo>()

	override fun applyCommands(commands: Array<out Command>) {
		fragmentManager.executePendingTransactions()

		//copy stack before apply commands
		copyStackToLocal()

		for (command in commands) {
			try {
				applyCommand(command)
			} catch (e: RuntimeException) {
				errorOnApplyCommand(command, e)
			}
		}
	}

	private fun copyStackToLocal() {
		localStackCopy.clear()
		for (i in 0 until fragmentManager.backStackEntryCount) {
			val str = fragmentManager.getBackStackEntryAt(i).name.orEmpty()
			localStackCopy.add(TransactionInfo.fromString(str))
		}
	}

	/**
	 * Perform transition described by the navigation command
	 *
	 * @param command the navigation command to apply
	 */
	protected open fun applyCommand(command: Command) {
		when (command) {
			is Forward -> forward(command)
			is Replace -> replace(command)
			is BackTo -> backTo(command)
			is Back -> back()
		}
	}

	protected open fun forward(command: Forward) {
		when (val screen = command.screen as AppScreen) {
			is ActivityScreen -> {
				checkAndStartActivity(screen)
			}
			is FragmentScreen -> {
				val type = if (command.clearContainer) REPLACE else ADD
				commitNewFragmentScreen(screen, type, true)
			}
		}
	}

	protected open fun replace(command: Replace) {
		when (val screen = command.screen as AppScreen) {
			is ActivityScreen -> {
				checkAndStartActivity(screen)
				activity.finish()
			}
			is FragmentScreen -> {
				if (localStackCopy.isNotEmpty()) {
					fragmentManager.popBackStack()
					val removed = localStackCopy.removeAt(localStackCopy.lastIndex)
					commitNewFragmentScreen(screen, removed.type, true)
				} else {
					commitNewFragmentScreen(screen, REPLACE, false)
				}
			}
		}
	}

	protected open fun back() {
		if (localStackCopy.isNotEmpty()) {
			fragmentManager.popBackStack()
			localStackCopy.removeAt(localStackCopy.lastIndex)
		} else {
			activityBack()
		}
	}

	protected open fun activityBack() {
		activity.finish()
	}

	protected open fun commitNewFragmentScreen(
		screen: FragmentScreen,
		type: TransactionInfo.Type,
		addToBackStack: Boolean
	) {
		val fragment = screen.createFragment(fragmentFactory)
		val transaction = fragmentManager.beginTransaction()
		transaction.setReorderingAllowed(true)
		if (addToBackStack) setupFragmentTransaction(
			transaction,
			fragmentManager.findFragmentById(containerId),
			fragment
		)
		when (type) {
			ADD -> transaction.add(containerId, fragment, screen.screenKey)
			REPLACE -> transaction.replace(containerId, fragment, screen.screenKey)
		}
		if (addToBackStack) {
			val transactionInfo = TransactionInfo(screen.screenKey, type)
			transaction.addToBackStack(transactionInfo.toString())
			localStackCopy.add(transactionInfo)
		}
		transaction.commit()
	}

	/**
	 * Performs [BackTo] command transition
	 */
	protected open fun backTo(command: BackTo) {
		if (command.screen == null) {
			backToRoot()
		} else {
			val screenKey = command.screen?.screenKey
			val index = localStackCopy.indexOfFirst { it.screenKey == screenKey }
			if (index != -1) {
				val forRemove = localStackCopy.subList(index, localStackCopy.size)
				fragmentManager.popBackStack(forRemove.first().toString(), 0)
				forRemove.clear()
			} else {
				backToUnexisting(command.screen as AppScreen)
			}
		}
	}

	private fun backToRoot() {
		localStackCopy.clear()
		fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
	}

	/**
	 * Override this method to setup fragment transaction [FragmentTransaction].
	 * For example: setCustomAnimations(...), addSharedElement(...) or setReorderingAllowed(...)
	 *
	 * @param fragmentTransaction fragment transaction
	 * @param currentFragment     current fragment in container
	 *                            (for [Replace] command it will be screen previous in new chain, NOT replaced screen)
	 * @param nextFragment        next screen fragment
	 */
	protected open fun setupFragmentTransaction(
		fragmentTransaction: FragmentTransaction,
		currentFragment: Fragment?,
		nextFragment: Fragment?
	) {
		fragmentTransaction.apply {
			setCustomAnimations(
				R.anim.slide_in,
				R.anim.slide_out,
				R.anim.pop_slide_in,
				R.anim.pop_slide_out
			)
		}
	}

	private fun checkAndStartActivity(screen: ActivityScreen) {
		// Check if we can start activity
		val activityIntent = screen.createIntent(activity)
		try {
			activity.startActivity(activityIntent, screen.startActivityOptions)
		} catch (e: ActivityNotFoundException) {
			unexistingActivity(screen, activityIntent)
		}
	}

	/**
	 * Called when there is no activity to open `screenKey`.
	 *
	 * @param screen         screen
	 * @param activityIntent intent passed to start Activity for the `screenKey`
	 */
	protected open fun unexistingActivity(
		screen: ActivityScreen,
		activityIntent: Intent
	) {
		// Do nothing by default
	}

	/**
	 * Called when we tried to fragmentBack to some specific screen (via [BackTo] command),
	 * but didn't found it.
	 *
	 * @param screen screen
	 */
	protected open fun backToUnexisting(screen: AppScreen) {
		backToRoot()
	}

	/**
	 * Override this method if you want to handle apply command error.
	 *
	 * @param command command
	 * @param error   error
	 */
	protected open fun errorOnApplyCommand(
		command: Command,
		error: RuntimeException
	) {
		throw error
	}
}