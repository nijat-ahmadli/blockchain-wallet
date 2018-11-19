package com.example.nijatahmadli.blockchainwallet.data.util

/**
 * A wrapper class to wrap successful/unsuccessful network results
 */
class NetworkResult<T> constructor(val data: T?, val message: String, val statusCode: Int) {

    constructor(data: T, statusCode: Int) : this(data, "", statusCode)

    constructor(message: String, statusCode: Int) : this(null, message, statusCode)

    companion object {

        var OK = 0
        var ERROR = 1
        var TIMEOUT = 2
        var ERROR_UNKNOWN = 3

        fun <T> createSuccessfulModel(data: T): NetworkResult<T> {
            return NetworkResult(data, OK)
        }

        fun <T> createUnsuccessfulModel(message: String): NetworkResult<T> {
            return NetworkResult(message, ERROR)
        }
    }

    val isSuccessful: Boolean
        get() = statusCode == OK
}