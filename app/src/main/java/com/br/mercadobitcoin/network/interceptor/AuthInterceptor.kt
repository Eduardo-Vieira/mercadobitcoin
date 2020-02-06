package com.br.mercadobitcoin.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        // Add token Authorization
        return chain.proceed(req)
    }
}