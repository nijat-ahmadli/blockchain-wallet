package com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel

import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.presentation.util.ConverterUtil
import com.example.nijatahmadli.blockchainwallet.presentation.util.DateUtil
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.math.BigInteger

class BcTransactionItemViewModelTest {

    companion object {
        private const val TIME_MILLIS = 1234567L
        private const val DATE = "06-11-2018"
        private val TSX_RESULT = BigInteger.valueOf(1234567)
        private val AMOUNT_BTC = BigDecimal(123456789)
        private val AMOUNT_BTC_NEGATIVE = BigDecimal(-123456789)
    }

    @Mock
    private lateinit var dateUtil: DateUtil
    @Mock
    private lateinit var converterUtil: ConverterUtil

    @InjectMocks
    private lateinit var sut: BcTransactionItemViewModel

    @Mock
    private lateinit var transaction: Transaction

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(transaction.time).thenReturn(TIME_MILLIS)
        `when`(transaction.result).thenReturn(TSX_RESULT)
        `when`(dateUtil.getDate(TIME_MILLIS)).thenReturn(DATE)
        `when`(converterUtil.satoshiToBtc(TSX_RESULT)).thenReturn(AMOUNT_BTC)
    }

    @Test
    fun setModel_should_setDate() {
        sut.setModel(transaction)

        assertThat(sut.date.get()).isEqualTo(DATE)
    }

    @Test
    fun setModel_should_setAmount() {
        sut.setModel(transaction)

        assertThat(sut.amount.get()).isEqualTo(AMOUNT_BTC.toString())
    }

    @Test
    fun setModel_should_setIsNegativeToTrue_when_amountIsNegative() {
        `when`(converterUtil.satoshiToBtc(TSX_RESULT)).thenReturn(AMOUNT_BTC_NEGATIVE)

        sut.setModel(transaction)

        assertThat(sut.isNegative.get()).isTrue()
    }

    @Test
    fun setModel_should_setIsNegativeToFalse_when_amountIsNotNegative() {
        `when`(converterUtil.satoshiToBtc(TSX_RESULT)).thenReturn(AMOUNT_BTC)

        sut.setModel(transaction)

        assertThat(sut.isNegative.get()).isFalse()
    }
}