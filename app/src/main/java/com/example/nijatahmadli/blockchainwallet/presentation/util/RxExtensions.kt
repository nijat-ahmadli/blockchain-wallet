package com.example.nijatahmadli.blockchainwallet.presentation.util

import com.example.nijatahmadli.blockchainwallet.presentation.base.viewmodel.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.addDisposable(baseViewModel: BaseViewModel) {
    baseViewModel.addDisposable(this)
}
