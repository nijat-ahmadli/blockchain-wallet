package com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel {

    internal var compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun initialize() {}

    override fun clean() {
        compositeDisposable.clear()
    }
}