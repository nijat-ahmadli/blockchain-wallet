package com.example.nijatahmadli.blockchainwallet.data.remote.api

import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkMultiAddress
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MultiAddressApi {

    @GET("multiaddr")
    fun getMultiAddress(@Query("active") address: String) : Single<NetworkMultiAddress>
}