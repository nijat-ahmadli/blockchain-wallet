package com.example.nijatahmadli.blockchainwallet.presentation.base

import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.ItemViewModel

interface ItemViewModelFactory<T> {

    fun createItemViewModel(): ItemViewModel<T>
}