package com.example.nijatahmadli.blockchainwallet.data.util

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * An extension function to convert Single to NetworkResult object
 * We catch network errors at this stage and wrap the result into NetworkResult object to handle later in presentation layer
 */
fun <T> Single<T>.toNetworkResult(networkResultHandler: NetworkResultHandler): Observable<NetworkResult<T>> {
    return this.toObservable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map { NetworkResult.createSuccessfulModel(it) }
        .onErrorResumeNext { throwable: Throwable -> networkResultHandler.handleNetworkException(throwable) }
}