package com.anesabml.quotey

import com.anesabml.quotey.core.data.IQuoteRepository
import com.anesabml.quotey.core.data.QuoteDataSource
import com.anesabml.quotey.core.domain.Quote

class FakeQuoteRepository(private var quoteDataSource: QuoteDataSource) : IQuoteRepository {
    override suspend fun getQod(): Quote =
        quoteDataSource.getQod()

    override suspend fun addQuote(quote: Quote) =
        quoteDataSource.addQuote(quote)


    override suspend fun addToFavorites(quote: Quote) =
        quoteDataSource.addToFavorites(quote)


    override suspend fun getFavorites(): List<Quote> =
        quoteDataSource.readFavorites()

    override suspend fun getAll(): List<Quote> =
        quoteDataSource.readAll()


    override suspend fun removeFromFavorites(quote: Quote) =
        quoteDataSource.removeFromFavorites(quote)
}