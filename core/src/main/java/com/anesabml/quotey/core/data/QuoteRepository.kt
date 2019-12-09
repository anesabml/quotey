package com.anesabml.quotey.core.data

import com.anesabml.quotey.core.domain.Quote

class QuoteRepository(private var quoteDataSource: QuoteDataSource) : IQuoteRepository {

    override suspend fun getQod() = quoteDataSource.getQod()

    override suspend fun addQuote(quote: Quote) = quoteDataSource.addQuote(quote)

    override suspend fun addToFavorites(quote: Quote) = quoteDataSource.addToFavorites(quote)

    override suspend fun getFavorites() = quoteDataSource.readFavorites()

    override suspend fun getAll() = quoteDataSource.readAll()

    override suspend fun removeFromFavorites(quote: Quote) = quoteDataSource.removeFromFavorites(quote)
}