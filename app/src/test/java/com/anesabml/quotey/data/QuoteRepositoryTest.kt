package com.anesabml.quotey.data

import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.framework.FakeQuoteDataSource
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteRepositoryTest {

    private val quotes = mutableListOf(
        Quote("1", "Quote", "Author"),
        Quote("1", "Quote", "Author"),
        Quote("1", "Quote", "Author")
    )

    private var quoteDataSource: FakeQuoteDataSource = FakeQuoteDataSource(quotes)

    @Test
    fun getQod_getRandomQuote() = runBlockingTest {
        val quote = quoteDataSource.getQod()

        assertNotNull(quote)
    }

    @Test
    fun addQuote() = runBlockingTest {
        // GIVEN - New Quote
        val newQuote = Quote("1", "Quote", "Author")

        // WHEN - Adding the quote
        quoteDataSource.addQuote(newQuote)
        quotes.add(newQuote)

        // THEN - The quote has been added
        val list = quoteDataSource.readAll()
        assertThat(list.size, `is`(quotes.size))
    }


    @Test
    fun addToFavorites() = runBlockingTest {
        // GIVEN - New Quote
        val newQuote = Quote("1", "Quote", "Author")

        // WHEN - Adding the quote
        quoteDataSource.addToFavorites(newQuote)
        quotes.add(newQuote)

        // THEN - The quote has been added
        val list = quoteDataSource.readAll()
        assertThat(list.size, `is`(quotes.size))
    }


    @Test
    fun getFavorites() = runBlockingTest {
        // WHEN - List of favorites
        val favorites = quoteDataSource.readFavorites()

        // THEN
        assertNotNull(favorites)
    }

    @Test
    fun getAll() = runBlockingTest {
        // WHEN - List of quotes
        val quotes = quoteDataSource.readFavorites()

        // THEN
        assertNotNull(quotes)
    }


    @Test
    fun removeFromFavorites() = runBlockingTest {
        // GIVEN - New Quote
        val newQuote = Quote("1", "QuoteFav", "Author")

        // WHEN - Adding the quote
        quoteDataSource.addQuote(newQuote)
        quoteDataSource.removeFromFavorites(newQuote)

        // THEN - The quote has been added
        val list = quoteDataSource.readAll()
        assertThat(list.size, `is`(quotes.size))
    }
}