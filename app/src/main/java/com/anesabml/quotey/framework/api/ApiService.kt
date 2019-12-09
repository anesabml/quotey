package com.anesabml.quotey.framework.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private const val BASE_URL = "http://quotes.rest/"

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val apiClient =
        OkHttpClient().newBuilder().addInterceptor(logging).build()

    private var client: Retrofit? = null

    private fun create(): Retrofit = Retrofit.Builder().client(apiClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    private fun getInstance(): Retrofit =
        (client ?: create()).also { client = it }

    val api: ApiInterface = getInstance().create(ApiInterface::class.java)

}