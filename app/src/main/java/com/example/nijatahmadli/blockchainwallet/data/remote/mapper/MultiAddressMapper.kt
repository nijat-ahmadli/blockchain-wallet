package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkMultiAddress
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkTransaction
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkWallet
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Wallet
import javax.inject.Inject

class MultiAddressMapper @Inject constructor(
    private val walletMapper: Mapper<NetworkWallet, Wallet>,
    private val transactionListMapper: Mapper<List<NetworkTransaction>, List<Transaction>>
) : Mapper<NetworkMultiAddress, MultiAddress> {

    override fun map(origin: NetworkMultiAddress?): MultiAddress {
        return origin?.let {
            MultiAddress(
                walletMapper.map(it.wallet),
                transactionListMapper.map(it.txs)
            )
        } ?: MultiAddress()
    }
}