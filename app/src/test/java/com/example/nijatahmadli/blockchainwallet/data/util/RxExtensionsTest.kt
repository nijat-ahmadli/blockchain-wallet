package com.example.nijatahmadli.blockchainwallet.data.util

import com.example.nijatahmadli.blockchainwallet.testutil.RxSchedulersOverrideRule
import com.google.common.truth.Truth.assertThat
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RxExtensionsTest {

    @Rule
    @JvmField
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var networkResultHandler: NetworkResultHandler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun toNetworkResult_should_returnSuccessfulNetworkResultObservable_when_singleHasNoErrors() {
        val data = Object()
        val single = Single.just(data)

        val testObserver = single.toNetworkResult(networkResultHandler).test()

        testObserver.assertComplete()
        val result = testObserver.values()[0]
        assertThat(result.data).isEqualTo(data)
        assertThat(result.statusCode).isEqualTo(NetworkResult.OK)
    }

    @Test
    fun toNetworkResult_should_returnUnSuccessfulNetworkResultObservable_when_singleHasErrors() {
        val throwable = Throwable()
        val single = Single.error<Any>(throwable)
        val networkResult = NetworkResult.createUnsuccessfulModel<Any>("error message")
        `when`(networkResultHandler.handleNetworkException<Any>(throwable)).thenReturn(Observable.just(networkResult))

        val testObserver = single.toNetworkResult(networkResultHandler).test()

        testObserver.assertComplete()
        val result = testObserver.values()[0]
        assertThat(result.message).isEqualTo(networkResult.message)
        assertThat(result.statusCode).isEqualTo(networkResult.statusCode)
    }
}