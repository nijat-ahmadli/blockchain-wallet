package com.example.nijatahmadli.blockchainwallet.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.nijatahmadli.blockchainwallet.BR
import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.ItemViewModel

class ItemViewHolder<in T>(
    itemView: View,
    private val itemViewModel: ItemViewModel<T>
) : RecyclerView.ViewHolder(itemView) {

    protected var binding: ViewDataBinding? = null

    init {
        createBinding()
    }

    private fun createBinding() {
        binding = DataBindingUtil.bind(itemView)
        binding?.setVariable(BR.viewModel, itemViewModel)
    }

    fun bind(model: T, position: Int) {
        itemViewModel.setModel(model, position)
        binding?.executePendingBindings()
    }

    fun clean() {}
}