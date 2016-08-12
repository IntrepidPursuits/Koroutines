package io.intrepid.koroutines.screens.dispatch

import android.os.Bundle
import io.intrepid.koroutines.R
import io.intrepid.koroutines.base.BaseFragment
import io.intrepid.koroutines.base.PresenterConfiguration
import io.intrepid.koroutines.screens.animation.AnimationActivity
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch

class DispatchFragment : BaseFragment<DispatchScreen, DispatchPresenter>(), DispatchScreen {

    private lateinit var launchJob: Job

    override val layoutResourceId: Int = R.layout.fragment_dispatch

    override fun createPresenter(configuration: PresenterConfiguration): DispatchPresenter {
        return DispatchPresenter(this, configuration)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        launchJob = launch {
            presenter.configureMeshNetwork()
        }
    }

    override fun launchSignInActivity() {
        closeThenAlphaToActivity<AnimationActivity>()
    }
}
