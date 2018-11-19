package com.example.nijatahmadli.blockchainwallet.data.util

import android.content.Context
import com.example.nijatahmadli.blockchainwallet.R
import io.reactivex.Observable
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkResultHandler @Inject constructor(private val context: Context) {

    fun <T> handleNetworkException(throwable: Throwable): Observable<NetworkResult<T>> {
        return Observable.just<NetworkResult<T>>(processNetworkException(throwable))
    }

    fun <T> processNetworkException(throwable: Throwable): NetworkResult<T> {
        return if (throwable is retrofit2.HttpException) {
            NetworkResult(throwable.message(), throwable.code())
        } else if (throwable is SocketTimeoutException) {
            NetworkResult(
                context.getString(R.string.network_error_timeout),
                NetworkResult.TIMEOUT
            )
        } else {
            NetworkResult(
                context.getString(R.string.network_error_unknown),
                NetworkResult.ERROR_UNKNOWN
            )
        }
    }
}