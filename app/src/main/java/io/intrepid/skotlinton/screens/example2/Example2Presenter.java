package io.intrepid.skotlinton.screens.example2;

import android.support.annotation.NonNull;

import io.intrepid.skotlinton.base.BasePresenter;
import io.intrepid.skotlinton.base.PresenterConfiguration;
import io.intrepid.skotlinton.utils.RxUtils;
import io.reactivex.disposables.Disposable;

public class Example2Presenter extends BasePresenter<Example2Contract.View> implements Example2Contract.Presenter {

    Example2Presenter(@NonNull Example2Contract.View view,
                      @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onViewCreated() {
        Disposable disposable = restApi.getMyIp()
                .compose(subscribeOnIoObserveOnUi())
                .subscribe(ipModel -> {
                    String ip = ipModel.ip;
                    view.showCurrentIpAddress(ip);
                    userSettings.setLastIp(ip);
                }, RxUtils.logError());
        disposables.add(disposable);

        String lastIp = userSettings.getLastIp();
        if (lastIp == null) {
            view.hidePreviousIpAddress();
        } else {
            view.showPreviousIpAddress(lastIp);
        }
    }
}
