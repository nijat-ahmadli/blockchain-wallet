package com.example.nijatahmadli.blockchainwallet.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BaseItemViewHolderFactory<T>(
    private val itemViewModelFactory: ItemViewModelFactory<T>,
    private val layoutResId: Int
) : ItemViewHolderFactory<T> {

    override fun createItemViewHolder(parent: ViewGroup): ItemViewHolder<T> {
        return ItemViewHolder(
            createItemView(parent, layoutResId),
            itemViewModelFactory.createItemViewModel()
        )
    }

    private fun createItemView(parent: ViewGroup, layoutRes: Int): View =
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
}