package io.intrepid.koroutines.app.screens.example1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import io.intrepid.koroutines.R
import io.intrepid.koroutines.app.base.BaseMvpActivity
import io.intrepid.koroutines.app.base.PresenterConfiguration
import io.intrepid.koroutines.app.screens.example2.Example2Activity
import io.intrepid.koroutines.utils.ButterKnife.bindView

class Example1Activity : BaseMvpActivity<Example1Contract.Presenter>(), Example1Contract.View {

    private val button: Button by bindView(R.id.fragment_example1_button)

    override val layoutResourceId: Int = R.layout.activity_example1

    override fun createPresenter(configuration: PresenterConfiguration): Example1Contract.Presenter {
        return Example1Presenter(this, configuration)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        button.setOnClickListener { presenter.onButtonClick() }
    }

    override fun gotoExample2() {
        val intent = Intent(this, Example2Activity::class.java)
        startActivity(intent)
    }
}
