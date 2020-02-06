package com.br.mercadobitcoin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.br.mercadobitcoin.database.dao.TickerDao
import com.br.mercadobitcoin.database.entity.Ticker
import com.br.mercadobitcoin.enumeration.EnumCoin
import com.br.mercadobitcoin.network.service.MBTC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeRepository(
    private val localData:TickerDao,
    private val remoteData: MBTC
    ): BaseRepository() {

    private val mediador = MediatorLiveData<Resource<List<Ticker>>?>()

    fun getTicker(): LiveData<Resource<List<Ticker>>?> {
        // Fazer busca interna no banco
        mediador.addSource(getLocalTicker()) { dataSuccess ->
            mediador.value = Resource(data = dataSuccess)
        }
        // Fazer atualização de dados que vem da api
        val falhasDaWebApiLiveData = MutableLiveData<Resource<List<Ticker>>?>()
        mediador.addSource(falhasDaWebApiLiveData) { resourceDeFalha ->
            val resourceAtual = mediador.value
            val resourceNovo: Resource<List<Ticker>>? = if (resourceAtual != null) {
                Resource(data = resourceAtual.data)
            } else {
                resourceDeFalha
            }
            mediador.value = resourceNovo
        }
        // Get na API
        getApi(quandoFalha = { erro ->
            falhasDaWebApiLiveData.value = Resource(data = null, erro = erro)
        })

        return mediador
    }

    private fun getLocalTicker(): LiveData<List<Ticker>> {
        return localData.getAll()
    }

    private suspend fun insertTicker(ticker: List<Ticker>){
        withContext(Dispatchers.IO) {
            localData.insertAll(ticker)
        }
    }

    private fun getApi(
        quandoFalha: (erro: String?) -> Unit
    ) {
        coroutineScope.launch {
            try {
                val ticker:ArrayList<Ticker> = arrayListOf()
                for(coin in EnumCoin.values()) {
                    //GET Ticker
                    val result = remoteData.getTicker(coin.name)
                    if (result.isSuccessful) {
                        ticker.add(
                            Ticker(
                                coin.name,
                                result.body()!!.ticker!!.high!!,
                                result.body()!!.ticker!!.low!!,
                                result.body()!!.ticker!!.vol!!,
                                result.body()!!.ticker!!.last!!,
                                result.body()!!.ticker!!.buy!!,
                                result.body()!!.ticker!!.sell!!,
                                result.body()!!.ticker!!.date!!
                            )
                        )
                    }
                }
                insertTicker(ticker)
            } catch (e: Throwable) {
                quandoFalha(e.message)
            }
        }
    }
}
