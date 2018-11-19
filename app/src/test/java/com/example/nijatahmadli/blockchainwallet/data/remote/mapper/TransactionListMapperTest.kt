package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkTransaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TransactionListMapperTest {

    @Mock
    private lateinit var transactionMapper: TransactionMapper

    @InjectMocks
    private lateinit var sut: TransactionListMapper

    private val networkTransactions = ArrayList<NetworkTransaction>()

    @Mock
    private lateinit var networkTransactionOne: NetworkTransaction
    @Mock
    private lateinit var networkTransactionTwo: NetworkTransaction
    @Mock
    private lateinit var transactionOne: Transaction
    @Mock
    private lateinit var transactionTwo: Transaction

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun map_should_returnEmptyList_when_networkTransactionListIsNull() {

        val result = sut.map(null)

        assertThat(result).isEmpty()
    }

    @Test
    fun map_should_returnCorrectTransactionList_when_networkTransactionListIsNotNull() {
        networkTransactions.add(networkTransactionOne)
        networkTransactions.add(networkTransactionTwo)
        `when`(transactionMapper.map(networkTransactionOne))
            .thenReturn(transactionOne)
        `when`(transactionMapper.map(networkTransactionTwo))
            .thenReturn(transactionTwo)

        val result = sut.map(networkTransactions)

        assertThat(result.size).isEqualTo(networkTransactions.size)
        assertThat(result[0]).isEqualTo(transactionOne)
        assertThat(result[1]).isEqualTo(transactionTwo)
    }
}