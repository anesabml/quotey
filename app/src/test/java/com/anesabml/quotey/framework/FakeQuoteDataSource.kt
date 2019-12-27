package com.anesabml.quotey.framework

import com.anesabml.quotey.core.data.IQuoteDataSource
import com.anesabml.quotey.core.domain.Quote

class FakeQuoteDataSource(var quotes: MutableList<Quote>? = mutableListOf()) : IQuoteDataSource {
    override suspend fun getQod(): Quote {
        return quotes?.first() ?: Quote.Empty
    }

    override suspend fun readAll(): List<Quote> {
        return quotes!!
    }

    override suspend fun readFavorites(): List<Quote> {
        return quotes!!
    }

    override suspend fun addQuote(quote: Quote) {
        quotes?.add(quote)
    }

    override suspend fun updateQuote(quote: Quote) {
        quotes?.remove(quote)
    }

}