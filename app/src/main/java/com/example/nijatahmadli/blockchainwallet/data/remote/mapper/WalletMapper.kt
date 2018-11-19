package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkWallet
import com.example.nijatahmadli.blockchainwallet.domain.model.Wallet
import javax.inject.Inject

class WalletMapper @Inject constructor() : Mapper<NetworkWallet, Wallet> {

    override fun map(origin: NetworkWallet?): Wallet {
        return origin?.let {
            Wallet(
                it.total_received,
                it.total_sent,
                it.final_balance
            )
        } ?: Wallet()
    }
}