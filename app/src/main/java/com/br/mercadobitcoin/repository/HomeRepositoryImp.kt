package com.br.mercadobitcoin.repository

import com.br.mercadobitcoin.database.dao.TickerDao
import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.network.service.MBTC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImp(
    private val localData:TickerDao,
    private val remoteData: MBTC
    ): HomeRepository {

    override suspend fun getLocalTicker(): List<Ticker> {
        return localData.getAll()
    }

    override suspend fun insertTicker(ticker: List<Ticker>){
        withContext(Dispatchers.IO) {
            localData.insertAll(ticker)
        }
    }

    override suspend fun getRemoteTicker(type:String) =
        withContext(Dispatchers.IO) {
            remoteData.getTicker(type)
        }
}
