package com.example.nijatahmadli.blockchainwallet.data.remote.model

import java.math.BigInteger

data class NetworkWallet(
    val total_received: BigInteger?,
    val total_sent: BigInteger?,
    val final_balance: BigInteger?
)