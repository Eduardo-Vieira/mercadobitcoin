package com.br.mercadobitcoin.interactor

import com.br.mercadobitcoin.database.entity.Ticker as TickerModel
import com.br.mercadobitcoin.repository.response.Ticker

object TickerMapper {

    fun convertTickerResponseToTickerModel(coinName:String, ticker: Ticker): TickerModel {
        return  TickerModel(coinName,
                        ticker.high,
                        ticker.low,
                        ticker.vol,
                        ticker.last,
                        ticker.buy,
                        ticker.sell,
                        ticker.date)
    }
}