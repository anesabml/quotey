package com.anesabml.quotey.core.data

import com.anesabml.quotey.core.domain.Quote

interface IQuoteRepository {
    suspend fun getQod(): Quote

    suspend fun addQuote(quote: Quote)

    suspend fun updateQuote(quote: Quote)

    suspend fun getFavorites(): List<Quote>

    suspend fun getAll(): List<Quote>

}