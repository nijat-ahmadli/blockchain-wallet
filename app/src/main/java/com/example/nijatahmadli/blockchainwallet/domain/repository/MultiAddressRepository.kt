package com.example.nijatahmadli.blockchainwallet.domain.repository

import com.example.nijatahmadli.blockchainwallet.data.util.NetworkResult
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import io.reactivex.Observable

interface MultiAddressRepository {

    fun getMultiAddress(): Observable<NetworkResult<MultiAddress>>
}