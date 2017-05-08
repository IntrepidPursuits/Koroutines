package io.intrepid.koroutines.app.screens.example3

import io.intrepid.koroutines.app.base.BasePresenter
import io.intrepid.koroutines.app.base.PresenterConfiguration

internal class Example3Presenter(view: Example3Contract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example3Contract.View>(view, configuration), Example3Contract.Presenter