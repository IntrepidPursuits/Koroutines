package io.intrepid.koroutines.smoke

import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import io.intrepid.koroutines.InstrumentationTestApplication
import io.intrepid.koroutines.rest.TestRestClient
import io.intrepid.koroutines.rules.MockServerRule
import io.intrepid.koroutines.settings.UserSettings
import io.intrepid.koroutines.testutils.BaseUiTest
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

@SmallTest
class ExampleSmokeTest : BaseUiTest() {
    @Rule
    @JvmField
    val mockServerRule = MockServerRule()
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(LandingActivity::class.java, true, false)

    @Mock
    internal lateinit var mockUserSettings: UserSettings

    @Before
    fun setUp() {
        InstrumentationTestApplication.overrideRestApi(TestRestClient.getRestApi(mockServerRule))
        InstrumentationTestApplication.overrideUserSettings(mockUserSettings)
    }

    //TODO start testing
//    @Test
//    fun smokeTest() {
//        activityTestRule.launchActivity(null)
//        mockServerRule.enqueueResponse(io.intrepid.koroutines.debug.test.R.raw.mock_ip)
//
//        `when`(mockUserSettings.lastIp).thenReturn("127.0.0.2")
//
//        onView(withId(R.id.example1_button)).perform(click())
//        onView(withId(R.id.example2_current_ip)).check(matches(withText("Your current Ip address is 127.0.0.1")))
//        onView(withId(R.id.example2_previous_ip)).check(matches(withText("Your previous Ip address is 127.0.0.2")))
//    }
}
