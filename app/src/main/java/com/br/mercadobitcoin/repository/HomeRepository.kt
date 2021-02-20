package com.br.mercadobitcoin.repository

import androidx.lifecycle.LiveData
import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.repository.response.TickerResponse
import retrofit2.Response

interface HomeRepository {
    suspend fun getLocalTicker(): List<Ticker>
    suspend fun insertTicker(ticker: List<Ticker>)
    suspend fun getRemoteTicker(type:String): Response<TickerResponse>
}