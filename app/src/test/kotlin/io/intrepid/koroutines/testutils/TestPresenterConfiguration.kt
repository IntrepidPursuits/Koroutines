package io.intrepid.koroutines.testutils

import io.intrepid.koroutines.base.PresenterConfiguration
import io.intrepid.koroutines.rest.RestApi
import io.intrepid.koroutines.settings.UserSettings
import kotlinx.coroutines.experimental.CommonPool
import org.mockito.Mockito
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.EmptyCoroutineContext

class TestPresenterConfiguration private constructor(commonPool: CoroutineContext, ui: CoroutineContext, userSettings: UserSettings, restApi: RestApi)
    : PresenterConfiguration(commonPool, ui, userSettings, restApi) {
    companion object {
        fun createTestConfiguration(): TestPresenterConfiguration {
            val userSettings = Mockito.mock(UserSettings::class.java)
            val restApi = Mockito.mock(RestApi::class.java)
            val commonPool = CommonPool
            val ui = EmptyCoroutineContext
            return TestPresenterConfiguration(commonPool, ui, userSettings, restApi)
        }
    }
}
