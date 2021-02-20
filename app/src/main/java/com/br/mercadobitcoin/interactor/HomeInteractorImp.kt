package com.br.mercadobitcoin.interactor

import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.enumeration.EnumCoin
import com.br.mercadobitcoin.repository.HomeRepositoryImp

class HomeInteractorImp(private val homeRepository: HomeRepositoryImp):HomeInteractor {

    override suspend fun getTickerList():ArrayList<Ticker> {
        val ticker:ArrayList<Ticker> = arrayListOf()
        for(coin in EnumCoin.values()) {
            val result = homeRepository.getRemoteTicker(coin.name)
            if(result.isSuccessful){
                result.body()?.let {
                    ticker.add(
                        TickerMapper
                            .convertTickerResponseToTickerModel(coin.name, it.ticker)
                    )
                }
            }
        }
        homeRepository.insertTicker(ticker)
        return ticker
    }

    override suspend fun getLocalTicker(): List<Ticker> = homeRepository.getLocalTicker()

}