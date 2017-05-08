package io.intrepid.koroutines.app.screens.example3.fragment

import android.os.Bundle
import android.widget.Button
import io.intrepid.koroutines.R
import io.intrepid.koroutines.app.base.BaseFragment
import io.intrepid.koroutines.app.base.PresenterConfiguration
import io.intrepid.koroutines.app.screens.example3.Example3Contract
import io.intrepid.koroutines.utils.ButterKnife.bindView
import timber.log.Timber

class Example3Fragment : BaseFragment<Example3Contract.Presenter, Example3FragmentContract.Presenter>(), Example3FragmentContract.View {

    private val button3: Button by bindView(R.id.fragment_example3_button)

    override val layoutResourceId: Int = R.layout.fragment_example3

    override fun createPresenter(configuration: PresenterConfiguration): Example3FragmentContract.Presenter {
        return Example3FragmentPresenter(this, configuration)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        button3.setOnClickListener { Timber.v("You clicked the button3") }
    }
}
