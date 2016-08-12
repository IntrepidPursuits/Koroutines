package io.intrepid.koroutines.screens.dispatch

import android.content.Intent
import android.support.v4.app.Fragment

import io.intrepid.koroutines.base.BaseFragmentActivity

class DispatchActivity : BaseFragmentActivity() {
    override fun createFragment(intent: Intent?): Fragment {
        return DispatchFragment()
    }
}
