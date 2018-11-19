package com.example.nijatahmadli.blockchainwallet.data.repository

import com.example.nijatahmadli.blockchainwallet.data.remote.api.MultiAddressApi
import com.example.nijatahmadli.blockchainwallet.data.remote.mapper.Mapper
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkMultiAddress
import com.example.nijatahmadli.blockchainwallet.data.util.NetworkResult
import com.example.nijatahmadli.blockchainwallet.data.util.NetworkResultHandler
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.testutil.RxSchedulersOverrideRule
import com.google.common.truth.Truth.assertThat
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class BcMultiAddressRepositoryTest {

    @Rule
    @JvmField
    val mOverrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var multiAddressApi: MultiAddressApi
    @Mock
    private lateinit var multiAddressMapper: Mapper<NetworkMultiAddress, MultiAddress>
    @Mock
    private lateinit var networkResultHandler: NetworkResultHandler
    private val address = "address"

    private lateinit var sut: BcMultiAddressRepository

    @Mock
    private lateinit var networkMultiAddress: NetworkMultiAddress

    @Mock
    private lateinit var multiAddress: MultiAddress

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = BcMultiAddressRepository(multiAddressApi, multiAddressMapper, networkResultHandler, address)
    }

    @Test
    fun getMultiAddress_should_returnMultiAddress() {
        val single = Single.just(networkMultiAddress)
        `when`(multiAddressApi.getMultiAddress(address)).thenReturn(single)
        `when`(multiAddressMapper.map(networkMultiAddress)).thenReturn(multiAddress)

        val testObserver = sut.getMultiAddress().test()

        testObserver.assertComplete()
        val result = testObserver.values()[0]
        assertThat(result.statusCode).isEqualTo(NetworkResult.OK)
        assertThat(result.data).isEqualTo(multiAddress)
    }
}