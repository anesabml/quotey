package com.anesabml.quotey

import com.anesabml.quotey.core.data.IQuoteRepository
import com.anesabml.quotey.core.data.IQuoteDataSource
import com.anesabml.quotey.core.domain.Quote

class AndroidTestFakeQuoteRepository(private var quoteDataSource: IQuoteDataSource) : IQuoteRepository {
    override suspend fun getQod(): Quote =
        quoteDataSource.getQod()

    override suspend fun addQuote(quote: Quote) =
        quoteDataSource.addQuote(quote)

    override suspend fun getFavorites(): List<Quote> =
        quoteDataSource.readFavorites()

    override suspend fun getAll(): List<Quote> =
        quoteDataSource.readAll()


    override suspend fun updateQuote(quote: Quote) =
        quoteDataSource.updateQuote(quote)
}