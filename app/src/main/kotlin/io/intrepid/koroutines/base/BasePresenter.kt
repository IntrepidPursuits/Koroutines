package io.intrepid.koroutines.base

import io.intrepid.koroutines.logging.CrashReporter
import io.intrepid.koroutines.rest.RestApi
import io.intrepid.koroutines.settings.UserSettings
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>(protected var view: V?, configuration: PresenterConfiguration) : BaseContract.Presenter {

    protected val ioScheduler: Scheduler = configuration.ioScheduler
    protected val uiScheduler: Scheduler = configuration.uiScheduler
    protected val userSettings: UserSettings = configuration.userSettings
    protected val restApi: RestApi = configuration.restApi
    protected val crashReporter: CrashReporter = configuration.crashReporter

    protected val disposables: CompositeDisposable = CompositeDisposable()

    private var isViewBound = false

    override fun onViewCreated() {

    }

    override fun bindView(view: BaseContract.View) {
        @Suppress("UNCHECKED_CAST")
        this.view = view as V
        if (!isViewBound) {
            onViewBound()
            isViewBound = true
        }
    }

    open protected fun onViewBound() {

    }

    override fun unbindView() {
        disposables.clear()
        this.view = null

        if (isViewBound) {
            onViewUnbound()
            isViewBound = false
        }
    }

    open protected fun onViewUnbound() {

    }

    /**
     * Note: The view will be null at this point. This method is for any additional cleanup that's not handled
     * by the CompositeDisposable
     */
    override fun onViewDestroyed() {

    }

    protected fun <R> subscribeOnIoObserveOnUi(): ObservableTransformer<R, R> = ObservableTransformer { it.subscribeOn(ioScheduler).observeOn(uiScheduler) }
}
