package io.intrepid.koroutines.app.screens.example1

import io.intrepid.koroutines.app.base.BasePresenter
import io.intrepid.koroutines.app.base.PresenterConfiguration

class Example1Presenter(view: Example1Contract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example1Contract.View>(view, configuration), Example1Contract.Presenter {
    override fun onButtonClick() {
        view?.gotoExample2()
    }
}
