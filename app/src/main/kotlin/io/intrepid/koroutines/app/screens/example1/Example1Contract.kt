package io.intrepid.koroutines.app.screens.example1

import io.intrepid.koroutines.app.base.BaseContract

interface Example1Contract {
    interface View : BaseContract.View{
        fun gotoExample2()
    }

    interface Presenter : BaseContract.Presenter{
        fun onButtonClick()
    }
}
