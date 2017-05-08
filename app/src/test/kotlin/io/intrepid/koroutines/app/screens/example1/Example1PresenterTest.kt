package io.intrepid.koroutines.app.screens.example1

import io.intrepid.koroutines.testutils.BasePresenterTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

internal class Example1PresenterTest : BasePresenterTest<Example1Presenter>() {

    @Mock
    lateinit var mockView: Example1Contract.View

    @Before
    fun setup() {
        presenter = Example1Presenter(mockView, testConfiguration)
    }

    @Test
    fun onButtonClick() {
    }
}
