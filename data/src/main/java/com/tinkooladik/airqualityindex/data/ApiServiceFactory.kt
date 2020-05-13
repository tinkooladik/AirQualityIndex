package com.tinkooladik.airqualityindex.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object ApiServiceFactory {

    fun makeService(
        url: String,
        isDebug: Boolean
    ): ApiService =
        makeService(
            url,
            makeClient(
                makeLogger(true)
            ),
            ApiService::class.java
        )

    private fun makeClient(
        logger: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

    private fun <T> makeService(
        url: String,
        okHttpClient: OkHttpClient,
        cl: Class<T>
    ): T =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(
                nonstrictJson.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(cl)

    private fun makeLogger(isDebug: Boolean) =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .apply {
                level = if (isDebug) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }
}