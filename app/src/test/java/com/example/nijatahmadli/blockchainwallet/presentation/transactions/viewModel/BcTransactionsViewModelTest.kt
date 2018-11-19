package com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel

import com.example.nijatahmadli.blockchainwallet.data.util.NetworkResult
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Wallet
import com.example.nijatahmadli.blockchainwallet.domain.repository.MultiAddressRepository
import com.example.nijatahmadli.blockchainwallet.presentation.util.ConverterUtil
import com.google.common.truth.Truth.assertThat
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.math.BigInteger

class BcTransactionsViewModelTest {

    private companion object {
        const val ERROR_MESSAGE = "errorMessage"
        val AMOUNT = BigInteger.valueOf(123456789)
        val AMOUNT_BTC = BigDecimal(123456789)
    }

    @Mock
    private lateinit var multiAddressRepository: MultiAddressRepository
    @Mock
    private lateinit var converterUtil: ConverterUtil

    @InjectMocks
    private lateinit var sut: BcTransactionsViewModel

    @Mock
    private lateinit var multiAddress: MultiAddress

    @Mock
    private lateinit var transactions: List<Transaction>

    @Mock
    private lateinit var wallet: Wallet

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(multiAddressRepository.getMultiAddress())
            .thenReturn(Observable.just(NetworkResult.createSuccessfulModel(multiAddress)))
        `when`(multiAddress.txs).thenReturn(transactions)
        `when`(multiAddress.wallet).thenReturn(wallet)
        `when`(wallet.final_balance).thenReturn(AMOUNT)
        `when`(wallet.total_received).thenReturn(AMOUNT)
        `when`(wallet.total_sent).thenReturn(AMOUNT)
        `when`(converterUtil.satoshiToBtc(AMOUNT)).thenReturn(AMOUNT_BTC)
    }

    @Test
    fun initialize_should_showError_when_getMultiAddressFails() {
        `when`(multiAddressRepository.getMultiAddress())
            .thenReturn(Observable.just(NetworkResult.createUnsuccessfulModel(ERROR_MESSAGE)))

        sut.initialize()

        assertThat(sut.showLoading.get()).isFalse()
        assertThat(sut.showRefresh.get()).isFalse()
        assertThat(sut.showError.get()).isTrue()
        assertThat(sut.showContent.get()).isFalse()
        assertThat(sut.errorMessage.get()).isEqualTo(ERROR_MESSAGE)
    }

    @Test
    fun initialize_should_setFinalBalance() {

        sut.initialize()

        assertContentState()
        assertThat(sut.finalBalance.get()).isEqualTo(AMOUNT_BTC.toString())
    }

    @Test
    fun initialize_should_setTotalReceived() {

        sut.initialize()

        assertContentState()
        assertThat(sut.totalReceived.get()).isEqualTo(AMOUNT_BTC.toString())
    }

    @Test
    fun initialize_should_setTotalSent() {

        sut.initialize()

        assertContentState()
        assertThat(sut.totalReceived.get()).isEqualTo(AMOUNT_BTC.toString())
    }

    @Test
    fun initialize_should_setTransactions() {
        val testObserver = sut.transactionsObservable.test()

        sut.initialize()

        assertContentState()
        testObserver.assertValue(transactions)
    }

    private fun assertContentState() {
        assertThat(sut.showLoading.get()).isFalse()
        assertThat(sut.showRefresh.get()).isFalse()
        assertThat(sut.showError.get()).isFalse()
        assertThat(sut.showContent.get()).isTrue()
    }
}