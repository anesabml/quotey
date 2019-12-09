package com.anesabml.quotey.framework

import com.anesabml.quotey.core.data.QuoteDataSource
import com.anesabml.quotey.core.domain.Quote

class FakeQuoteDataSource(var quotes: MutableList<Quote>? = mutableListOf()) : QuoteDataSource {
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

    override suspend fun addToFavorites(quote: Quote) {
        quotes?.add(quote)
    }

    override suspend fun removeFromFavorites(quote: Quote) {
        quotes?.remove(quote)
    }

}