package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkMultiAddress
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkTransaction
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkWallet
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Wallet
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MultiAddressMapperTest {

    @Mock
    private lateinit var walletMapper: Mapper<NetworkWallet, Wallet>
    @Mock
    private lateinit var transactionListMapper: Mapper<List<NetworkTransaction>, List<Transaction>>

    private lateinit var sut: MultiAddressMapper

    @Mock
    private lateinit var networkMultiAddress: NetworkMultiAddress
    @Mock
    private lateinit var networkWallet: NetworkWallet
    @Mock
    private lateinit var wallet: Wallet
    @Mock
    private lateinit var networkTransactions: List<NetworkTransaction>
    @Mock
    private lateinit var transactions: List<Transaction>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MultiAddressMapper(walletMapper, transactionListMapper)
    }

    @Test
    fun map_should_returnEmptyMultiAddress_when_networkMultiAddressIsNull() {

        val result = sut.map(null)

        assertThat(result).isEqualTo(MultiAddress())
    }

    @Test
    fun map_should_returnCorrectMultiAddress_when_networkMultiAddressIsNotNull() {
        `when`(networkMultiAddress.wallet).thenReturn(networkWallet)
        `when`(networkMultiAddress.txs).thenReturn(networkTransactions)
        `when`(walletMapper.map(networkWallet)).thenReturn(wallet)
        `when`(transactionListMapper.map(networkTransactions)).thenReturn(transactions)

        val result = sut.map(networkMultiAddress)

        assertThat(result.wallet).isEqualTo(wallet)
        assertThat(result.txs).isEqualTo(transactions)
    }
}