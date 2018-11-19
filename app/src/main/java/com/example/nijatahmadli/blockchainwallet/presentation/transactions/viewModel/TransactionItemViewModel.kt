package com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.ItemViewModel

interface TransactionItemViewModel : ItemViewModel<Transaction> {

    val date: ObservableField<String>
    val amount: ObservableField<String>
    val isNegative: ObservableBoolean
}