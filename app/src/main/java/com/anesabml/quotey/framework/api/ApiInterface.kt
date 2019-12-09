package com.anesabml.quotey.framework.api

import com.anesabml.quotey.core.domain.QuoteApiResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("qod.json")
    suspend fun getRandomQuote(): Response<QuoteApiResponse>


}