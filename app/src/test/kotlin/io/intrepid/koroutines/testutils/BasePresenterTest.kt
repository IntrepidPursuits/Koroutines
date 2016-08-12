package io.intrepid.koroutines.testutils

import io.intrepid.koroutines.base.BasePresenter
import io.intrepid.koroutines.base.BaseScreen
import io.intrepid.koroutines.rest.RestApi
import io.intrepid.koroutines.settings.UserSettings
import org.junit.Before
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

abstract class BasePresenterTest<S : BaseScreen, P : BasePresenter<S>> {
    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    protected lateinit var presenter: P
    protected lateinit var testConfiguration: TestPresenterConfiguration
    protected lateinit var mockRestApi: RestApi
    protected lateinit var mockUserSettings: UserSettings

    @Before
    fun baseSetup() {
        testConfiguration = TestPresenterConfiguration.createTestConfiguration()
        mockRestApi = testConfiguration.restApi
        mockUserSettings = testConfiguration.userSettings
    }
}
