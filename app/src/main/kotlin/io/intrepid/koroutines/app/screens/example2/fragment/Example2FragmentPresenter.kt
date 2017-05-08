package io.intrepid.koroutines.app.screens.example2.fragment

import io.intrepid.koroutines.R
import io.intrepid.koroutines.api.models.IpModel
import io.intrepid.koroutines.app.base.BasePresenter
import io.intrepid.koroutines.app.base.PresenterConfiguration
import java.util.concurrent.TimeUnit

internal class Example2FragmentPresenter(view: Example2FragmentContract.View, configuration: PresenterConfiguration)
    : BasePresenter<Example2FragmentContract.View>(view, configuration), Example2FragmentContract.Presenter {

    override fun onViewCreated() {
        super.onViewCreated()
        fetchIpAddressSubscription()
        bindUserSettingsIp()
    }

    private fun fetchIpAddressSubscription() {
        disposables.add(restApi.getMyIp()
                .delay(2, TimeUnit.SECONDS)
                .compose(singleSubscribeOnIoObserveOnUi())
                .compose(singleProgressDialog(view, R.string.fetching_ip))
                .doOnSuccess { updateIpValue(it) }
                .doOnError { logError() }
                .subscribe())
    }

    private fun bindUserSettingsIp() {
        val lastIp = userSettings.lastIp
        if (lastIp.isEmpty()) {
            view?.hidePreviousIpAddress()
        } else {
            view?.showPreviousIpAddress(lastIp)
        }
    }

    private fun updateIpValue(model: IpModel) {
        val ip = model.ip
        view?.showCurrentIpAddress(ip)
        userSettings.lastIp = ip
    }
}
