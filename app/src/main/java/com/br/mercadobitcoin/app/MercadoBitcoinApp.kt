package com.br.mercadobitcoin.app

import android.app.Application
import com.br.mercadobitcoin.di.daoModule
import com.br.mercadobitcoin.di.databaseModule
import com.br.mercadobitcoin.di.networkModule
import com.br.mercadobitcoin.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MercadoBitcoinApp: Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this.applicationContext)

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@MercadoBitcoinApp)
            // declare modules
            modules(listOf(
                databaseModule,
                networkModule,
                daoModule,
                viewModelModule
            ))
        }
    }
}