package io.intrepid.koroutines.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.intrepid.koroutines.rest.RestApi
import io.intrepid.koroutines.settings.UserSettings
import io.intrepid.koroutines.utils.LogUtils
import io.intrepid.koroutines.utils.LogUtils.logVerboseMessage
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

abstract class BasePresenter<S : BaseScreen>(protected var screen: S?, configuration: PresenterConfiguration) : LifecycleObserver {
    private val lifeCycleJob: Job = Job()

    protected val ioContext: CoroutineContext = configuration.ioContext + lifeCycleJob
    protected val uiContext: CoroutineContext = configuration.uiContext + lifeCycleJob
    protected val userSettings: UserSettings = configuration.userSettings
    protected val restApi: RestApi = configuration.restApi

    private var isViewBound = true

    init {
        @Suppress("LeakingThis")
        logVerboseMessage(this, "Presenter onCreatePresenter")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeScreenLifecycle() {
        if (!isViewBound) {
            this.screen = screen
            logVerboseMessage(this, "Presenter onResumeScreenLifecycle")
            isViewBound = true
            screenStarted()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseScreenLifecycle() {
        if (isViewBound) {
            this.screen = null
            logVerboseMessage(this, "Presenter onPauseScreenLifecycle")
            isViewBound = false
            screenStopped()
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyScreenLifecycle() {
        logVerboseMessage(this, "Presenter onDestroyScreenLifecycle")
        lifeCycleJob.cancel()
        lifeCycleJob.printStatus()
        screenDestroyed()
    }

    open protected fun screenStarted() {}

    open protected fun screenStopped() {}

    open protected fun screenDestroyed() {}
}

fun Job.printStatus() {
    launch(UI) {
        LogUtils.logVerboseMessage(this, "LifeCycle Job Status = isActive: $isActive | isCancelled: $isCancelled | isComplete: $isCompleted")
    }
}