package com.anesabml.quotey.data

import com.anesabml.quotey.domain.model.Quote

class QuoteRepository(private var quoteDataSource: IQuoteDataSource) :
    IQuoteRepository {

    override suspend fun getQod() = quoteDataSource.getQod()

    override suspend fun addQuote(quote: Quote) = quoteDataSource.addQuote(quote)

    override suspend fun updateQuote(quote: Quote) = quoteDataSource.updateQuote(quote)

    override suspend fun getFavorites() = quoteDataSource.readFavorites()

    override suspend fun getAll() = quoteDataSource.readAll()

}