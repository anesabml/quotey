package com.anesabml.quotey.core.data

import com.anesabml.quotey.core.domain.model.Quote

interface IQuoteDataSource {
    suspend fun getQod(): Quote

    suspend fun addQuote(quote: Quote)

    suspend fun updateQuote(quote: Quote)

    suspend fun readAll(): List<Quote>

    suspend fun readFavorites(): List<Quote>
}