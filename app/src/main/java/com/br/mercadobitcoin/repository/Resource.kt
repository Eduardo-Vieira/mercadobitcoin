package com.br.mercadobitcoin.repository

class Resource<T>(
    val data: T?,
    val erro: String? = null,
    val code:Int? = 0
)