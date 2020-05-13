package com.tinkooladik.airqualityindex.data

import okhttp3.Interceptor
import okhttp3.Response

private const val KEY_TOKEN = "token"

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter(KEY_TOKEN, token)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}