package com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel

interface ItemViewModel<in T> {

    fun setModel(model: T)

    fun setModel(model: T, position: Int) {
        setModel(model)
    }
}