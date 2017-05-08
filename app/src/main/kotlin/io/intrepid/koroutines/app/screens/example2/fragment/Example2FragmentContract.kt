package io.intrepid.koroutines.app.screens.example2.fragment

interface Example2FragmentContract {
    interface View : io.intrepid.koroutines.app.base.BaseContract.View {
        fun showCurrentIpAddress(ip: String)

        fun showPreviousIpAddress(ip: String)

        fun hidePreviousIpAddress()
    }

    interface Presenter : io.intrepid.koroutines.app.base.BaseContract.Presenter
}
