package com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.ViewModel
import io.reactivex.Observable

interface TransactionsViewModel : ViewModel {

    val showContent: ObservableBoolean
    val showLoading: ObservableBoolean
    val showRefresh: ObservableBoolean
    val showError: ObservableBoolean
    val errorMessage: ObservableField<String>
    val finalBalance: ObservableField<String>
    val totalReceived: ObservableField<String>
    val totalSent: ObservableField<String>

    val transactionsObservable: Observable<List<Transaction>>

    fun refresh()
}