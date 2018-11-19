package com.example.nijatahmadli.blockchainwallet.presentation.transactions.factory

import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.presentation.base.ItemViewModelFactory
import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.ItemViewModel
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel.BcTransactionItemViewModel
import com.example.nijatahmadli.blockchainwallet.presentation.util.ConverterUtil
import com.example.nijatahmadli.blockchainwallet.presentation.util.DateUtil
import javax.inject.Inject

class TransactionItemViewModelFactory @Inject constructor(
    private val dateUtil: DateUtil,
    private val converterUtil: ConverterUtil
) : ItemViewModelFactory<Transaction> {

    override fun createItemViewModel(): ItemViewModel<Transaction> {
        return BcTransactionItemViewModel(dateUtil, converterUtil)
    }
}