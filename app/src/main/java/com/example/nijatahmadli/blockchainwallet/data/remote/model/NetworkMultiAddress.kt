package com.example.nijatahmadli.blockchainwallet.data.remote.model

data class NetworkMultiAddress(
    val wallet: NetworkWallet?,
    val txs: List<NetworkTransaction>?
)