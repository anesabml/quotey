package com.anesabml.quotey.framework.api

import com.anesabml.quotey.core.domain.Quote
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("random")
    suspend fun getRandomQuote(): Response<Quote>


}