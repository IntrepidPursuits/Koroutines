package io.intrepid.koroutines.screens.example1

import io.intrepid.koroutines.base.BaseContract

interface Example1Contract {
    interface View : BaseContract.View {

        fun gotoExample2()
    }

    interface Presenter : BaseContract.Presenter {

        fun onButtonClick()
    }
}
