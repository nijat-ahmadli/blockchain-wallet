package com.example.nijatahmadli.blockchainwallet.di

import com.example.nijatahmadli.blockchainwallet.R
import com.example.nijatahmadli.blockchainwallet.data.remote.api.MultiAddressApi
import com.example.nijatahmadli.blockchainwallet.data.remote.mapper.*
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkMultiAddress
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkTransaction
import com.example.nijatahmadli.blockchainwallet.data.remote.model.NetworkWallet
import com.example.nijatahmadli.blockchainwallet.data.repository.BcMultiAddressRepository
import com.example.nijatahmadli.blockchainwallet.data.repository.BcMultiAddressRepository.Companion.KEY_ADDRESS
import com.example.nijatahmadli.blockchainwallet.domain.model.MultiAddress
import com.example.nijatahmadli.blockchainwallet.domain.model.Transaction
import com.example.nijatahmadli.blockchainwallet.domain.model.Wallet
import com.example.nijatahmadli.blockchainwallet.domain.repository.MultiAddressRepository
import com.example.nijatahmadli.blockchainwallet.presentation.base.BaseItemViewHolderFactory
import com.example.nijatahmadli.blockchainwallet.presentation.base.ItemViewHolderFactory
import com.example.nijatahmadli.blockchainwallet.presentation.base.ItemViewModelFactory
import com.example.nijatahmadli.blockchainwallet.presentation.base.RxRecyclerViewAdapter
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.factory.TransactionItemViewModelFactory
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel.BcTransactionItemViewModel
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel.BcTransactionsViewModel
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel.TransactionItemViewModel
import com.example.nijatahmadli.blockchainwallet.presentation.transactions.viewModel.TransactionsViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class TransactionsActivityModule {

    @Provides
    @ActivityScope
    fun provideTransactionsViewModel(viewModel: BcTransactionsViewModel): TransactionsViewModel = viewModel

    @Provides
    @ActivityScope
    fun provideTransactionItemViewModel(itemViewModel: BcTransactionItemViewModel): TransactionItemViewModel =
        itemViewModel

    @Provides
    @ActivityScope
    fun provideHomeBannersRecyclerViewAdapter(
        itemViewHolderFactory: ItemViewHolderFactory<Transaction>
    ): RxRecyclerViewAdapter<Transaction> = RxRecyclerViewAdapter(itemViewHolderFactory)

    @Provides
    @ActivityScope
    fun provideHomeBannerItemViewHolderFactory(
        itemViewModelFactory: ItemViewModelFactory<Transaction>
    ): ItemViewHolderFactory<Transaction> =
        BaseItemViewHolderFactory(itemViewModelFactory, R.layout.transaction_item_view)

    @Provides
    @ActivityScope
    fun provideHomeBannerItemViewModelFactory(
        factory: TransactionItemViewModelFactory
    ): ItemViewModelFactory<Transaction> = factory
}