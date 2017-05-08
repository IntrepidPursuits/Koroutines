package io.intrepid.koroutines.app


import io.intrepid.koroutines.app.base.BasePresenter
import io.intrepid.koroutines.app.base.PresenterConfiguration

class ApplicationPresenter(view: ApplicationContract.View, configuration: PresenterConfiguration) :
        BasePresenter<ApplicationContract.View>(view, configuration), ApplicationContract.Presenter
