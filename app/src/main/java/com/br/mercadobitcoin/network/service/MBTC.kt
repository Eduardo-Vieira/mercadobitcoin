package com.br.mercadobitcoin.network.service

import com.br.mercadobitcoin.network.response.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MBTC {
    @GET("{coin}/ticker")
    suspend fun getTicker(@Path("coin") coin: String): Response<TickerResponse>
}