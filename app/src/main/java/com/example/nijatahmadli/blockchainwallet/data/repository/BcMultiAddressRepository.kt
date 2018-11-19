package com.example.nijatahmadli.blockchainwallet.data.repository

import com.example.nijatahmadli.blockchainwallet.data.remote.api.MultiAddressApi
import com.example.nijatahmadli.blockchainwallet.data.remote.mapper.Mapper
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkMultiAddress
import com.example.nijatahmadli.blockchainwallet.data.util.*
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.domain.repository.MultiAddressRepository
import io.reactivex.Observable
import javax.inject.*

/**
 * If were to implement a cache layer we could have intermediate interfaces to choose the data source i.e. Remote or Cache
 * In the context of this task, and for the sake of simplicity we directly invoke api i.e. Remote data source
 */
class BcMultiAddressRepository @Inject constructor(
    private val multiAddressApi: MultiAddressApi,
    private val multiAddressMapper: Mapper<NetworkMultiAddress, MultiAddress>,
    private val networkResultHandler: NetworkResultHandler,
    @param:Named(KEY_ADDRESS) private val address: String
) : MultiAddressRepository {

    // As it is not known where to get the Address in the context of this task, I just injected it
    companion object {
        const val KEY_ADDRESS = "address"
    }

    override fun getMultiAddress(): Observable<NetworkResult<MultiAddress>> {
        return multiAddressApi
            .getMultiAddress(address)
            .map { multiAddressMapper.map(it) }
            .toNetworkResult(networkResultHandler)
    }
}