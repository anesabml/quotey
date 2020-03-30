package com.anesabml.quotey.framework.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.anesabml.quotey.core.domain.model.Quote
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@MediumTest
class QuoteyDatabaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: QuoteyDatabase
    private lateinit var quoteDao: QuoteDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, QuoteyDatabase::class.java).build()
        quoteDao = db.quoteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun addQuote() = runBlockingTest {
        db.clearAllTables()
        // Given a quote
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length
        )

        // When adding the quote to db
        quoteDao.addQuote(quoteEntity)

        // Then test the results
        val expectedQuote = quoteDao.getAll().first()

        assertThat(quote.id).containsMatch(expectedQuote.id)
        assertThat(quote.quote).containsMatch(expectedQuote.quote)
    }

    @Test
    fun updateQuote_setIsFavoriteToTrue() = runBlockingTest {
        db.clearAllTables()
        // Given a quote and add it to db
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length
        )
        quoteDao.addQuote(quoteEntity)

        // When updating the quote in db
        quoteEntity.isFavorite = true
        quoteDao.updateQuote(quoteEntity)

        // Then test the results
        val expectedQuote = quoteDao.getAll().first()

        assertThat(quote.id).containsMatch(expectedQuote.id)
        assertThat(quote.quote).containsMatch(expectedQuote.quote)
        assertThat(expectedQuote.isFavorite).isTrue()
    }

    @Test
    fun updateQuote_setIsFavoriteToFalse() = runBlockingTest {
        db.clearAllTables()
        // Given a quote and add it to db
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length
        )
        quoteDao.addQuote(quoteEntity)

        // When updating the quote in db
        quoteEntity.isFavorite = false
        quoteDao.updateQuote(quoteEntity)

        // Then test the results
        val expectedQuote = quoteDao.getAll().first()

        assertThat(quote.id).containsMatch(expectedQuote.id)
        assertThat(quote.quote).containsMatch(expectedQuote.quote)
        assertThat(expectedQuote.isFavorite).isFalse()
    }

    @Test
    fun getQuotes_returnsEmpty() = runBlockingTest {
        db.clearAllTables()
        // Given an empty list of quotes
        val quotes = mutableListOf<QuoteEntity>()

        // When getting the quotes
        quotes.addAll(quoteDao.getAll())

        // Then test if its empty
        assertThat(quotes).isEmpty()
    }

    @Test
    fun getQuotes_returnsNonEmpty() = runBlockingTest {
        db.clearAllTables()
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length
        )
        quoteDao.addQuote(quoteEntity)

        // Given an empty list of quotes
        val quotes = mutableListOf<QuoteEntity>()

        // When getting the quotes
        quotes.addAll(quoteDao.getAll())

        // Then test if its empty
        assertThat(quotes).isNotEmpty()
    }

    @Test
    fun getFavorites_returnsNonEmpty() = runBlockingTest {
        db.clearAllTables()
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length,
            isFavorite = true
        )
        quoteDao.addQuote(quoteEntity)

        // Given an empty list of quotes
        val quotes = mutableListOf<QuoteEntity>()

        // When getting the quotes
        quotes.addAll(quoteDao.getFavorites())

        // Then test if its empty
        assertThat(quotes).isNotEmpty()
    }

    @Test
    fun getFavorites_returnsEmpty() = runBlockingTest {
        db.clearAllTables()
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length,
            isFavorite = false
        )
        quoteDao.addQuote(quoteEntity)

        // Given an empty list of quotes
        val quotes = mutableListOf<QuoteEntity>()

        // When getting the quotes
        quotes.addAll(quoteDao.getFavorites())

        // Then test if its empty
        assertThat(quotes).isEmpty()
    }

    @Test
    fun removeQuote_returnsEmpty() = runBlockingTest {
        db.clearAllTables()
        val quote = Quote.Empty
        val quoteEntity = QuoteEntity(
            id = quote.id,
            quote = quote.quote,
            author = quote.author,
            background = quote.background,
            category = quote.category,
            title = quote.title,
            length = quote.length
        )
        quoteDao.addQuote(quoteEntity)

        // Given an empty list of quotes
        quoteDao.removeQuote(quoteEntity)

        // When getting the quotes
        val quotes = quoteDao.getAll()

        // Then test if its empty
        assertThat(quotes).isEmpty()
    }

}