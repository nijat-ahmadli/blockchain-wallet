package com.example.nijatahmadli.blockchainwallet.domain.model

data class MultiAddress(
    val wallet: Wallet,
    val txs: List<Transaction>
) {
    companion object {
        operator fun invoke(
            wallet: Wallet? = null,
            txs: List<Transaction>? = null
        ) = MultiAddress(
            wallet ?: Wallet(),
            txs ?: emptyList()
        )
    }
}