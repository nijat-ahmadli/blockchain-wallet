package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkTransaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigInteger

class TransactionMapperTest {

    private lateinit var sut: TransactionMapper

    @Mock
    private lateinit var networkTransaction: NetworkTransaction

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = TransactionMapper()
    }

    @Test
    fun map_should_returnEmptyTransaction_when_networkTransactionIsNull() {

        val result = sut.map(null)

        assertThat(result).isEqualTo(Transaction())
    }

    @Test
    fun map_should_returnCorrectTransaction_when_networkTransactionIsNotNull() {
        val tsxResult = BigInteger.valueOf(12345)
        val time = 123456789L
        `when`(networkTransaction.result).thenReturn(tsxResult)
        `when`(networkTransaction.time).thenReturn(time)

        val result = sut.map(networkTransaction)

        assertThat(result.result).isEqualTo(tsxResult)
        assertThat(result.time).isEqualTo(time)
    }
}