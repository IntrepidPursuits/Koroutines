package io.intrepid.koroutines.app.screens.example3

import android.os.Bundle
import io.intrepid.koroutines.R
import io.intrepid.koroutines.app.base.BaseMvpActivity
import io.intrepid.koroutines.app.base.PresenterConfiguration
import io.intrepid.koroutines.app.screens.example2.fragment.Example2Fragment
import io.intrepid.koroutines.app.screens.example3.fragment.Example3Fragment

class Example3Activity : BaseMvpActivity<Example3Contract.Presenter>(), Example3Contract.View {

    override val layoutResourceId: Int = R.layout.activity_layout_3
    var fragment1 = Example2Fragment()

    override fun createPresenter(configuration: PresenterConfiguration): Example3Contract.Presenter {
        return Example3Presenter(this, configuration)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            fragment1 = Example2Fragment()
            val fragment2 = Example3Fragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_example3_fragment1, fragment1)
                    .add(R.id.activity_example3_fragment2, fragment2)
                    .commit()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        fragment1.hideActivity3Button()
    }
}
