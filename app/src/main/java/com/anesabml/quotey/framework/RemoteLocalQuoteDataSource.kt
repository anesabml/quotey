package com.anesabml.quotey.framework

import android.content.Context
import com.anesabml.quotey.core.data.QuoteDataSource
import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.framework.api.ApiService
import com.anesabml.quotey.framework.db.QuoteEntity
import com.anesabml.quotey.framework.db.QuoteyDatabase

class RemoteLocalQuoteDataSource(context: Context) : QuoteDataSource {

    private var quoteDao = QuoteyDatabase.getInstance(context).quoteDao()
    private var api = ApiService.api

    override suspend fun addQuote(quote: Quote) {
        quoteDao.addQuote(
            QuoteEntity(
                id = quote.id,
                quote = quote.quote,
                author = quote.author,
                background = quote.background,
                category = quote.category,
                title = quote.title,
                length = quote.length
            )
        )
    }

    override suspend fun addToFavorites(quote: Quote) {
        quoteDao.updateQuote(
            QuoteEntity(
                id = quote.id,
                quote = quote.quote,
                author = quote.author,
                background = quote.background,
                category = quote.category,
                title = quote.title,
                length = quote.length,
                isFavorite = true
            )
        )
    }

    override suspend fun removeFromFavorites(quote: Quote) = quoteDao.removeQuote(
        QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length,
            isFavorite = false
        )
    )

    override suspend fun getQod(): Quote {
        return api.getRandomQuote().body()?.contents?.quotes?.firstOrNull() ?: Quote.Empty
    }

    override suspend fun readFavorites(): List<Quote> {
        return quoteDao.getFavorites().map {
            Quote(
                id = it.id,
                quote = it.quote,
                author = it.author,
                background = it.background,
                category = it.category,
                title = it.title,
                length = it.length
            )
        }
    }

    override suspend fun readAll(): List<Quote> {
        return quoteDao.getAll().map {
            Quote(
                id = it.id,
                quote = it.quote,
                author = it.author,
                background = it.background,
                category = it.category,
                title = it.title,
                length = it.length
            )
        }
    }
}