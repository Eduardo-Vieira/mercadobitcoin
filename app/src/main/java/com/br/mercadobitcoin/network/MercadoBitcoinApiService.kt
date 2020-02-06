package com.br.mercadobitcoin.network

import com.br.mercadobitcoin.BuildConfig
import com.br.mercadobitcoin.network.interceptor.AuthInterceptor
import com.br.mercadobitcoin.network.service.MBTC
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideGson(): Gson {
    return GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.URL_BASE)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor()) // Stetho debug network
        .build()
}

fun provideBTCService(retrofit: Retrofit):MBTC = retrofit.create(MBTC::class.java)