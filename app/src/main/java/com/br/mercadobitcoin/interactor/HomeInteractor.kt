package com.br.mercadobitcoin.interactor

import com.br.mercadobitcoin.database.entity.Ticker

interface HomeInteractor {
    suspend fun getTickerList(): ArrayList<Ticker>
    suspend fun getLocalTicker(): List<Ticker>
}