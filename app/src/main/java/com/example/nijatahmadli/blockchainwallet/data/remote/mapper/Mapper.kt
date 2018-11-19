package com.example.nijatahmadli.blockchainwallet.data.remote.mapper

interface Mapper<O, R> {

    fun map(origin: O?): R
}