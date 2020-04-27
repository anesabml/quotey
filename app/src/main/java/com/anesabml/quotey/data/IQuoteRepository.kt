package com.anesabml.quotey.data

import com.anesabml.quotey.domain.model.Quote

interface IQuoteRepository {

    suspend fun getQod(): Quote

    suspend fun addQuote(quote: Quote)

    suspend fun updateQuote(quote: Quote)

    suspend fun getFavorites(): List<Quote>

    suspend fun getAll(): List<Quote>

}