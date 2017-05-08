package io.intrepid.koroutines.app.base

import android.support.annotation.StringRes
import io.intrepid.koroutines.api.rest.RestApi
import io.intrepid.koroutines.app.base.logging.CrashReporter
import io.intrepid.koroutines.app.base.settings.UserSettings
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import timber.log.Timber

abstract class BasePresenter<V : BaseContract.App>(protected var view: V?, configuration: PresenterConfiguration) : BaseContract.Presenter {
    protected val ioScheduler: Scheduler = configuration.ioScheduler
    protected val uiScheduler: Scheduler = configuration.uiScheduler
    protected val userSettings: UserSettings = configuration.userSettings
    protected val restApi: RestApi = configuration.restApi
    protected val crashReporter: CrashReporter = configuration.crashReporter

    protected val disposables: CompositeDisposable = CompositeDisposable()

    private var isViewBound = false

    override fun onViewCreated() {

    }

    override fun bindView(view: BaseContract.App) {
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
    protected fun <R> singleSubscribeOnIoObserveOnUi(): SingleTransformer<R, R> = SingleTransformer { it.subscribeOn(ioScheduler).observeOn(uiScheduler) }
    /**
     * Show a progress dialog at the beginning and end of the Single flow.
     */
    protected fun <R> singleProgressDialog(screen: BaseContract.App?, @StringRes message: Int): SingleTransformer<R, R> = SingleTransformer {
        when (screen) {
            is BaseContract.View? -> {
                it.doOnSubscribe { screen?.showProgressDialog(message) }
                        .doAfterSuccess { screen?.hideProgressDialog() }
            }
            else -> {
                it
            }
        }
    }

    protected fun logError(): Consumer<Throwable> {
        return Consumer { Timber.w(it, "observable stream encountered an error") }
    }
}
