package com.anesabml.quotey

import com.anesabml.quotey.core.data.IQuoteRepository
import com.anesabml.quotey.core.data.IQuoteDataSource
import com.anesabml.quotey.core.domain.Quote

class AndroidTestFakeQuoteRepository(private var quoteDataSource: IQuoteDataSource) : IQuoteRepository {
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