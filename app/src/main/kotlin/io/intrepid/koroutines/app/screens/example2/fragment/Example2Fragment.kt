package io.intrepid.koroutines.app.screens.example2.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import io.intrepid.koroutines.R
import io.intrepid.koroutines.app.base.BaseContract
import io.intrepid.koroutines.app.base.BaseFragment
import io.intrepid.koroutines.app.base.PresenterConfiguration
import io.intrepid.koroutines.app.screens.example3.Example3Activity
import io.intrepid.koroutines.utils.ButterKnife.bindView

class Example2Fragment : BaseFragment<BaseContract.Presenter, Example2FragmentContract.Presenter>(), Example2FragmentContract.View {

    private val currentIpView: TextView by bindView(R.id.fragment_example2_current_ip)
    private val previousIpView: TextView by bindView(R.id.fragment_example2_previous_ip)
    private val button2: Button by bindView(R.id.fragment_example2_button)
    private val CURRENT = "current"
    private val PREVIOUS = "previous"

    override val layoutResourceId: Int = R.layout.fragment_example2

    override fun createPresenter(configuration: PresenterConfiguration): Example2FragmentContract.Presenter {
        return Example2FragmentPresenter(this, configuration)
    }


    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        button2.setOnClickListener {
            val intent = Intent(activity, Example3Activity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showCurrentIpAddress(ip: String) {
        // This should be extracted to string resource in a real app, but we are inlining this for the
        // example so that string.xml is not cluttered up with example texts
        currentIpView.text = getIpMessage(CURRENT, ip)
    }

    @SuppressLint("SetTextI18n")
    override fun showPreviousIpAddress(ip: String) {
        previousIpView.visibility = VISIBLE
        previousIpView.text = getIpMessage(PREVIOUS, ip)
    }

    private fun getIpMessage(modifier: String, ip: String) = "Your $modifier Ip address is $ip"

    override fun hidePreviousIpAddress() {
        previousIpView.visibility = GONE
    }

    fun hideActivity3Button() {
        button2.visibility = View.GONE
    }
}
