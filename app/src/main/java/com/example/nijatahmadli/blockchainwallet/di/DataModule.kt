package com.example.nijatahmadli.blockchainwallet.di

import com.example.nijatahmadli.blockchainwallet.data.remote.api.MultiAddressApi
import com.example.nijatahmadli.blockchainwallet.data.remote.mapper.*
import com.example.nijatahmadli.blockchainwallet.data.remote.model.*
import com.example.nijatahmadli.blockchainwallet.data.repository.BcMultiAddressRepository
import com.example.nijatahmadli.blockchainwallet.domain.model.*
import com.example.nijatahmadli.blockchainwallet.domain.repository.MultiAddressRepository
import dagger.*
import retrofit2.Retrofit
import javax.inject.*

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMultiAddressApi(retrofit: Retrofit): MultiAddressApi {
        return retrofit.create(MultiAddressApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMultiAddressRepository(repository: BcMultiAddressRepository): MultiAddressRepository {
        return repository
    }

    @Provides
    @Singleton
    @Named(BcMultiAddressRepository.KEY_ADDRESS)
    fun provideAddress(): String {
        return "xpub6CfLQa8fLgtouvLxrb8EtvjbXfoC1yqzH6YbTJw4dP7srt523AhcMV8Uh4K3TWSHz9oDWmn9MuJogzdGU3ncxkBsAC9wFBLmFrWT9Ek81kQ"
    }

    @Provides
    @Singleton
    fun provideMultiAddressMapper(
        mapper: MultiAddressMapper
    ): Mapper<NetworkMultiAddress, MultiAddress> = mapper

    @Provides
    @Singleton
    fun provideWalletMapper(mapper: WalletMapper): Mapper<NetworkWallet, Wallet> = mapper

    @Provides
    @Singleton
    fun provideTransactionListMapper(
        mapper: TransactionListMapper
    ): Mapper<List<NetworkTransaction>, List<Transaction>> = mapper

    @Provides
    @Singleton
    fun provideTransactionMapper(
        mapper: TransactionMapper
    ): Mapper<NetworkTransaction, Transaction> = mapper
}