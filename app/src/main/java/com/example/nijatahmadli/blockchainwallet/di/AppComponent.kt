package com.example.nijatahmadli.blockchainwallet.di

import android.app.Application
import com.example.nijatahmadli.blockchainwallet.BlockchainWalletApplication
import dagger.*
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        RetrofitModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BlockchainWalletApplication)
}