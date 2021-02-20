package com.br.mercadobitcoin.di

import androidx.room.Room
import com.br.mercadobitcoin.BuildConfig
import com.br.mercadobitcoin.interactor.HomeInteractorImp
import com.br.mercadobitcoin.database.AppDatabase
import com.br.mercadobitcoin.network.interceptor.AuthInterceptor
import com.br.mercadobitcoin.network.provideBTCService
import com.br.mercadobitcoin.network.provideOkHttpClient
import com.br.mercadobitcoin.network.provideRetrofit
import com.br.mercadobitcoin.repository.HomeRepositoryImp
import com.br.mercadobitcoin.ui.fragment.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }
}

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideBTCService(get()) }
    single { provideRetrofit(get()) }
}

val daoModule = module {
    //DAO
    single { get<AppDatabase>().tickerDao() }

    //Repository
    single { HomeRepositoryImp(get(),get()) }
    //Interactor
    single { HomeInteractorImp(get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            get()
        )
    }
}