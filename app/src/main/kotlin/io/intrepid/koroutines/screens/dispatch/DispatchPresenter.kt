package io.intrepid.koroutines.screens.dispatch

import io.intrepid.koroutines.base.BasePresenter
import io.intrepid.koroutines.base.PresenterConfiguration
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import java.util.concurrent.TimeUnit

class DispatchPresenter(screen: DispatchScreen, configuration: PresenterConfiguration) : BasePresenter<DispatchScreen>(screen, configuration) {
    suspend fun configureMeshNetwork() {
        delay(3, TimeUnit.SECONDS)
        async(uiContext) {
            screen?.launchSignInActivity()
        }.await()
    }
}
