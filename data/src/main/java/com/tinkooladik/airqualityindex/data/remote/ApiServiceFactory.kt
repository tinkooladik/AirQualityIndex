package com.tinkooladik.airqualityindex.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tinkooladik.airqualityindex.data.BuildConfig
import com.tinkooladik.airqualityindex.data.nonstrictJson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object ApiServiceFactory {

    fun makeService(
        isDebug: Boolean
    ): ApiService =
        makeService(
            makeClient(
                makeLogger(isDebug),
                AuthInterceptor(BuildConfig.API_TOKEN)
            ),
            ApiService::class.java
        )

    private fun <T> makeService(
        okHttpClient: OkHttpClient,
        cl: Class<T>
    ): T =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(
                nonstrictJson.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(cl)

    private fun makeClient(
        logger: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(authInterceptor)
            .build()

    private fun makeLogger(isDebug: Boolean) =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .apply {
                level = if (isDebug) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }
}