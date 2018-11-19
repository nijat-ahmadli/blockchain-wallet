package com.example.nijatahmadli.blockchainwallet.presentation.util

import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class ConverterUtil @Inject constructor() {

    fun satoshiToBtc(satoshi: BigInteger): BigDecimal {
        val satoshiBigDecimal = BigDecimal(satoshi)
        val tenToPowerOfEight = BigDecimal(Math.pow(10.0, 8.0))
        return satoshiBigDecimal.divide(tenToPowerOfEight, 8, BigDecimal.ROUND_HALF_UP)
    }
}