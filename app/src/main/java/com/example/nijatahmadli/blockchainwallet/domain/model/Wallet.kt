package com.example.nijatahmadli.blockchainwallet.domain.model

import java.math.BigInteger

data class Wallet(
    val total_received: BigInteger,
    val total_sent: BigInteger,
    val final_balance: BigInteger
) {
    companion object {
        operator fun invoke(
            total_received: BigInteger? = null,
            total_sent: BigInteger? = null,
            final_balance: BigInteger? = null
        ) = Wallet(
            total_received ?: BigInteger.valueOf(0),
            total_sent ?: BigInteger.valueOf(0),
            final_balance ?: BigInteger.valueOf(0)
        )
    }
}