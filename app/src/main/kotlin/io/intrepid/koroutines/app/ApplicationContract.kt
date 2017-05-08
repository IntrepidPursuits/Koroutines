package io.intrepid.koroutines.app


import io.intrepid.koroutines.app.base.BaseContract

class ApplicationContract {
    interface View : BaseContract.App

    interface Presenter : BaseContract.Presenter
}
