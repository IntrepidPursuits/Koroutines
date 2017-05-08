package io.intrepid.koroutines.app.screens.example3.fragment

import io.intrepid.koroutines.app.base.BasePresenter
import io.intrepid.koroutines.app.base.PresenterConfiguration

internal class Example3FragmentPresenter(view: Example3FragmentContract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example3FragmentContract.View>(view, configuration), Example3FragmentContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
    }
}
