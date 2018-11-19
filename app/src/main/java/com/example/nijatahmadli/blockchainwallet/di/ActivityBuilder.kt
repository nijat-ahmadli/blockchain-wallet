package com.example.nijatahmadli.blockchainwallet.di

import com.example.nijatahmadli.blockchainwallet.presentation.transactions.view.TransactionsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [TransactionsActivityModule::class])
    abstract fun bindTransactionsActivity(): TransactionsActivity
}