package com.example.nijatahmadli.blockchainwallet.presentation.base

import android.view.ViewGroup

interface ItemViewHolderFactory<T> {

    fun createItemViewHolder(parent: ViewGroup): ItemViewHolder<T>
}