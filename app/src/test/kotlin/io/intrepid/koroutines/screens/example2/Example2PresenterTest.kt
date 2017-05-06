package io.intrepid.koroutines.screens.example2

import io.intrepid.koroutines.models.IpModel
import io.intrepid.koroutines.testutils.BasePresenterTest
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

internal class Example2PresenterTest : BasePresenterTest<Example2Presenter>() {
    @Mock
    lateinit var mockView: Example2Contract.View

    @Before
    fun setup() {
        presenter = Example2Presenter(mockView, testConfiguration)
    }

    @Test
    fun onViewCreated() {
        val mockIp = "127.0.0.1"
        val mockPreviousIp = "127.0.0.2"

        val mockIpModel = IpModel()
        mockIpModel.ip = mockIp
        `when`(mockRestApi.getMyIp()).thenReturn(Observable.just(mockIpModel))
        `when`(mockUserSettings.lastIp).thenReturn(mockPreviousIp)

        presenter.onViewCreated()
        verify(mockView).showPreviousIpAddress(mockPreviousIp)
        testConfiguration.triggerRxSchedulers()
        verify(mockView).showCurrentIpAddress(mockIp)
        verify(mockUserSettings).lastIp = mockIp
    }

    @Test
    @Throws(Exception::class)
    fun onViewCreated_NoPreviousIp() {
        `when`(mockRestApi.getMyIp()).thenReturn(Observable.empty<IpModel>())
        `when`(mockUserSettings.lastIp).thenReturn("")

        presenter.onViewCreated()
        verify(mockView).hidePreviousIpAddress()
    }
}
