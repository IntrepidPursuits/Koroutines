package io.intrepid.koroutines.app.base

import android.support.annotation.StringRes

class BaseContract {
    interface App{
        fun showToast(@StringRes messageResource: Int)
    }
    interface View : App{
        fun showProgressDialog(@StringRes messageResource: Int)

        fun hideProgressDialog()
    }

    interface Presenter {
        fun bindView(view: App)

        fun unbindView()

        fun onViewCreated()

        fun onViewDestroyed()
    }
}
