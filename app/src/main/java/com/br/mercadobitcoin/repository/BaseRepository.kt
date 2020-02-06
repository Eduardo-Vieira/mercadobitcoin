package com.br.mercadobitcoin.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseRepository {

    private val repositoryJob = Job()
    protected val coroutineScope = CoroutineScope(repositoryJob + Dispatchers.Main)

}