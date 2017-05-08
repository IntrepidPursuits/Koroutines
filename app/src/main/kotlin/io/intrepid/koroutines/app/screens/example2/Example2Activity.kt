package io.intrepid.koroutines.app.screens.example2

import android.content.Intent
import android.support.v4.app.Fragment

import io.intrepid.koroutines.app.base.BaseFragmentActivity
import io.intrepid.koroutines.app.screens.example2.fragment.Example2Fragment

class Example2Activity : BaseFragmentActivity() {

    override fun createFragment(intent: Intent?): Fragment {
        return Example2Fragment()
    }
}
