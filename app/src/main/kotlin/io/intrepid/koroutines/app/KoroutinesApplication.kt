package io.intrepid.koroutines.app

import io.intrepid.koroutines.app.base.BaseApplication
import io.intrepid.koroutines.app.base.PresenterConfiguration

open class KoroutinesApplication : BaseApplication<ApplicationContract.Presenter>(), ApplicationContract.View {
    override fun createPresenter(configuration: PresenterConfiguration): ApplicationContract.Presenter {
        return ApplicationPresenter(this, configuration)
    }
}
