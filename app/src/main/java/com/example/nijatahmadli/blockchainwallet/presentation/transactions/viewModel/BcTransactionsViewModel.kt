package com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.nijatahmadli.blockchainwallet.data.util.NetworkResult
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.domain.repository.MultiAddressRepository
import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.BaseViewModel
import com.example.nijatahmadli.blockchainwallet.presentation.util.ConverterUtil
import com.example.nijatahmadli.blockchainwallet.presentation.util.addDisposable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class BcTransactionsViewModel @Inject constructor(
    private val multiAddressRepository: MultiAddressRepository,
    private val converterUtil: ConverterUtil
) : BaseViewModel(), TransactionsViewModel {

    override val showContent = ObservableBoolean()
    override val showLoading = ObservableBoolean()
    override val showRefresh = ObservableBoolean()
    override val showError = ObservableBoolean()
    override val errorMessage = ObservableField<String>()
    override val finalBalance = ObservableField<String>()
    override val totalReceived = ObservableField<String>()
    override val totalSent = ObservableField<String>()

    private val transactionsSubject: PublishSubject<List<Transaction>> = PublishSubject.create()
    override val transactionsObservable: Observable<List<Transaction>> = transactionsSubject.hide()

    override fun initialize() {
        super.initialize()
        showLoading.set(true)
        getMultiAddress()
    }

    private fun getMultiAddress() {
        multiAddressRepository
            .getMultiAddress()
            .subscribe(this::processMultiAddressResponse)
            .addDisposable(this)
    }

    private fun processMultiAddressResponse(it: NetworkResult<MultiAddress>) {
        showLoading.set(false)
        showRefresh.set(false)
        if (it.isSuccessful) {
            showContent.set(true)
            showError.set(false)
            setFields(it.data)
        } else {
            showError.set(true)
            showContent.set(false)
            errorMessage.set(it.message)
        }
    }

    private fun setFields(multiAddress: MultiAddress?) {
        with(multiAddress!!) {
            finalBalance.set(converterUtil.satoshiToBtc(wallet.final_balance).toString())
            totalReceived.set(converterUtil.satoshiToBtc(wallet.total_received).toString())
            totalSent.set(converterUtil.satoshiToBtc(wallet.total_sent).toString())
            transactionsSubject.onNext(txs)
        }
    }

    override fun refresh() {
        showRefresh.set(true)
        getMultiAddress()
    }
}