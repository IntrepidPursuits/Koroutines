package io.intrepid.koroutines

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.intrepid.koroutines.base.PresenterConfiguration
import io.intrepid.koroutines.logging.CrashlyticsReporter
import io.intrepid.koroutines.logging.TimberConfig
import io.intrepid.koroutines.rest.RetrofitClient
import io.intrepid.koroutines.settings.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

open class KoroutinesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LeakCanary.install(this)

        CrashlyticsReporter.init(this)

        TimberConfig.init(CrashlyticsReporter)

        initCalligraphy()
    }

    private fun initCalligraphy() {
        val config = CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.Roboto_Regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        CalligraphyConfig.initDefault(config)
    }

    open fun getPresenterConfiguration(): PresenterConfiguration {
        return PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                SharedPreferencesManager.getInstance(this),
                RetrofitClient.restApi,
                CrashlyticsReporter
        )
    }
}
