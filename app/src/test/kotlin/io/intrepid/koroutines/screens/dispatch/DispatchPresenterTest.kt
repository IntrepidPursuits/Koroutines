package io.intrepid.koroutines.screens.dispatch

import io.intrepid.koroutines.testutils.BasePresenterTest
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

internal class DispatchPresenterTest : BasePresenterTest<DispatchScreen, DispatchPresenter>() {

    @Mock
    protected lateinit var mockView: DispatchScreen

    @Before
    fun setup() {
        presenter = DispatchPresenter(mockView, testConfiguration)
    }

    @Test
    fun configureMeshNetwork() = runBlocking {
        presenter.configureMeshNetwork()
        verify(mockView, times(1)).launchSignInActivity()
    }
}
