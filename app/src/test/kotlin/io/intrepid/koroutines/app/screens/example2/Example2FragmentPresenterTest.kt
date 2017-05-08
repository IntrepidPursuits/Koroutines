package io.intrepid.koroutines.app.screens.example2

import io.intrepid.koroutines.app.screens.example2.fragment.Example2FragmentContract
import io.intrepid.koroutines.app.screens.example2.fragment.Example2FragmentPresenter
import io.intrepid.koroutines.testutils.BasePresenterTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

internal class Example2FragmentPresenterTest : BasePresenterTest<Example2FragmentPresenter>() {
    @Mock
    lateinit var mockView: Example2FragmentContract.View

    @Before
    fun setup() {
        presenter = Example2FragmentPresenter(mockView, testConfiguration)
    }

    @Test
    fun onViewCreated() {
    }
}
