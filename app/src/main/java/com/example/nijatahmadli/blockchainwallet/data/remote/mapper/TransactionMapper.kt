package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkTransaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import javax.inject.Inject

class TransactionMapper @Inject constructor() : Mapper<NetworkTransaction, Transaction> {

    override fun map(origin: NetworkTransaction?): Transaction {
        return origin?.let {
            Transaction(
                it.result,
                it.time
            )
        } ?: Transaction()
    }
}