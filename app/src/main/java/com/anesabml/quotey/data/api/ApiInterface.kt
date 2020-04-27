package com.anesabml.quotey.data.api

import com.anesabml.quotey.domain.model.Quote
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("random")
    suspend fun getRandomQuote(): Response<Quote>


}