package io.intrepid.koroutines.app.base

import android.app.Application
import android.app.ProgressDialog
import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.widget.Toast
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.leakcanary.LeakCanary
import io.intrepid.koroutines.api.rest.OkHttpFactory
import io.intrepid.koroutines.api.rest.RetrofitClient
import io.intrepid.koroutines.app.base.logging.CrashlyticsReporter
import io.intrepid.koroutines.app.base.logging.TimberConfig
import io.intrepid.koroutines.app.base.settings.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class BaseApplication<P : BaseContract.Presenter> : Application(), BaseContract.App {
    private val userSettings by lazy { SharedPreferencesManager.getInstance(this) }
    private val okHttpFactory by lazy { OkHttpFactory.getInstance(userSettings, ChuckInterceptor(this)) }
    private val retrofitClient by lazy { RetrofitClient.getInstance(okHttpFactory.client) }

    open fun presenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                userSettings,
                retrofitClient.restApi,
                CrashlyticsReporter)
    }

    private lateinit var progressDialog: ProgressDialog
    protected lateinit var presenter: P

    @CallSuper
    override fun onCreate() {
        Timber.v("Lifecycle onCreate: $this")
        super.onCreate()
        initializeGlobalDependencies()
        presenter = createPresenter(presenterConfiguration())

        presenter.bindView(this)
        presenter.onViewCreated()
    }

    override fun onTerminate() {
        Timber.v("Lifecycle onCreate: $this")
        presenter.onViewDestroyed()
        presenter.unbindView()
        super.onTerminate()
    }

    abstract fun createPresenter(configuration: PresenterConfiguration): P

    private fun initializeGlobalDependencies() {
        CrashlyticsReporter.init(this)

        TimberConfig.init(CrashlyticsReporter)

        LeakCanary.install(this)
    }

    override fun showToast(@StringRes messageResource: Int) {
        Toast.makeText(this, messageResource, Toast.LENGTH_SHORT).show()
    }
}
