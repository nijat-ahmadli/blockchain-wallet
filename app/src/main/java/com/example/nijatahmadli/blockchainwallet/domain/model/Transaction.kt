package com.example.nijatahmadli.blockchainwallet.domain.model

import java.math.BigInteger

data class Transaction(
    val result: BigInteger,
    val time: Long
) {
    companion object {
        operator fun invoke(
            result: BigInteger? = null,
            time: Long? = null
        ) = Transaction(
            result ?: BigInteger.valueOf(0),
            time ?: 0
        )
    }
}