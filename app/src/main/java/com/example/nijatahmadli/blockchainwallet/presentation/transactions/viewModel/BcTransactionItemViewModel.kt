package com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.presentation.util.ConverterUtil
import com.example.nijatahmadli.blockchainwallet.presentation.util.DateUtil
import java.math.BigDecimal
import javax.inject.Inject

class BcTransactionItemViewModel @Inject constructor(
    private val dateUtil: DateUtil,
    private val converterUtil: ConverterUtil
) : TransactionItemViewModel {

    override val date = ObservableField<String>()
    override val amount = ObservableField<String>()
    override val isNegative = ObservableBoolean()

    private var transaction: Transaction? = null

    override fun setModel(model: Transaction) {
        this.transaction = model

        date.set(dateUtil.getDate(model.time))
        val amountBtc = converterUtil.satoshiToBtc(model.result)
        amount.set(amountBtc.toString())
        isNegative.set(amountBtc < BigDecimal(0))
    }
}