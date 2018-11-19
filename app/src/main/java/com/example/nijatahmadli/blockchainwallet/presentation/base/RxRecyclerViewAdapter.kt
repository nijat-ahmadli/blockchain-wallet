package com.example.nijatahmadli.blockchainwallet.presentation.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RxRecyclerViewAdapter<T> @Inject constructor(
    private val itemViewHolderFactory: ItemViewHolderFactory<T>
) : RecyclerView.Adapter<ItemViewHolder<T>>() {

    private val items = ArrayList<T>()
    private val itemViewHolderList: MutableList<ItemViewHolder<T>> = ArrayList()
    private val compositeDisposable = CompositeDisposable()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder<T>, position: Int) {
        itemViewHolder.bind(items[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<T> {
        val itemViewHolder = itemViewHolderFactory.createItemViewHolder(parent)
        itemViewHolderList.add(itemViewHolder)
        return itemViewHolder
    }

    fun subscribeToSource(source: Observable<List<T>>) {
        compositeDisposable.add(source.subscribe { setSource(it) })
    }

    private fun setSource(source: List<T>) {
        items.clear()
        items.addAll(source)
        notifyDataSetChanged()
    }
    
    fun clean() {
        compositeDisposable.clear()
        cleanViewHolders()
    }

    private fun cleanViewHolders() {
        for (viewHolder in itemViewHolderList) {
            viewHolder.clean()
        }
    }
}