package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkWallet
import com.example.nijatahmadli.blockchainwallet.domain.model.Wallet
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigInteger


class WalletMapperTest {

    private lateinit var sut: WalletMapper

    @Mock
    private lateinit var networkWallet: NetworkWallet

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = WalletMapper()
    }

    @Test
    fun map_should_returnEmptyWallet_when_networkWalletIsNull() {

        val result = sut.map(null)

        assertThat(result).isEqualTo(Wallet())
    }

    @Test
    fun map_should_returnCorrectWallet_when_networkWalletIsNotNull() {
        val totalReceived = BigInteger.valueOf(12345)
        val totalSent = BigInteger.valueOf(123)
        val finalBalance = BigInteger.valueOf(123456789)
        `when`(networkWallet.total_received).thenReturn(totalReceived)
        `when`(networkWallet.total_sent).thenReturn(totalSent)
        `when`(networkWallet.final_balance).thenReturn(finalBalance)

        val result = sut.map(networkWallet)

        assertThat(result.total_received).isEqualTo(totalReceived)
        assertThat(result.total_sent).isEqualTo(totalSent)
        assertThat(result.final_balance).isEqualTo(finalBalance)
    }
}