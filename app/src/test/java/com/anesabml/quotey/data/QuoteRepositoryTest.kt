package com.anesabml.quotey.data

import com.anesabml.quotey.domain.model.Quote
import com.anesabml.quotey.framework.FakeQuoteDataSource
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class QuoteRepositoryTest {

    private val quotes = mutableListOf(
        Quote(1, "Quote", "Author", 10),
        Quote(1, "Quote", "Author", 10),
        Quote(1, "Quote", "Author", 10)
    )

    private lateinit var quoteRepository: QuoteRepository

    @Before
    fun init() {
        val quoteDataSource = FakeQuoteDataSource(quotes)
        quoteRepository =
            QuoteRepository(quoteDataSource)
    }

    @Test
    fun getQod_getRandomQuote() = runBlockingTest {
        val quote = quoteRepository.getQod()

        assertNotNull(quote)
    }

    @Test
    fun addQuote() = runBlockingTest {
        // GIVEN - New Quote
        val newQuote =
            Quote(1, "Quote", "Author", 10)

        // WHEN - Adding the quote
        quoteRepository.addQuote(newQuote)
        quotes.add(newQuote)

        // THEN - The quote has been added
        val list = quoteRepository.getAll()
        assertThat(list.size, `is`(quotes.size))
    }


    @Test
    fun addToFavorites() = runBlockingTest {
        // GIVEN - New Quote
        val newQuote =
            Quote(1, "Quote", "Author", 10)

        // WHEN - Adding the quote
        quoteRepository.updateQuote(newQuote)
        quotes.add(newQuote)

        // THEN - The quote has been added
        val list = quoteRepository.getAll()
        assertThat(list.size, `is`(quotes.size))
    }


    @Test
    fun getFavorites() = runBlockingTest {
        // WHEN - List of favorites
        val favorites = quoteRepository.getFavorites()

        // THEN
        assertNotNull(favorites)
    }

    @Test
    fun getAll() = runBlockingTest {
        // WHEN - List of quotes
        val quotes = quoteRepository.getFavorites()

        // THEN
        assertNotNull(quotes)
    }


    @Test
    fun removeFromFavorites() = runBlockingTest {
        // GIVEN - New Quote
        val newQuote =
            Quote(1, "Quote", "Author", 10)

        // WHEN - Adding the quote
        quoteRepository.addQuote(newQuote)
        quoteRepository.updateQuote(newQuote)

        // THEN - The quote has been added
        val list = quoteRepository.getAll()
        assertThat(list.size, `is`(quotes.size))
    }
}