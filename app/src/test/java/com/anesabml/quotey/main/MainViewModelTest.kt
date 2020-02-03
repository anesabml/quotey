package com.anesabml.quotey.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anesabml.quotey.core.data.QuoteRepository
import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.core.interactors.*
import com.anesabml.quotey.framework.FakeQuoteDataSource
import com.anesabml.quotey.framework.Interactors
import com.anesabml.quotey.getOrAwaitValue
import com.anesabml.quotey.ui.main.MainViewModel
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.hamcrest.core.IsNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var interactors: Interactors

    @Before
    fun setupViewModel() {
        val quotes = mutableListOf(
            Quote("1", "Quote", "Author"),
            Quote("1", "Quote", "Author"),
            Quote("1", "Quote", "Author")
        )

        val quoteDataSource = FakeQuoteDataSource(quotes)
        val repository = QuoteRepository(quoteDataSource)
        interactors = Interactors(
            GetRandomQuote(repository),
            AddQuote(repository),
            UpdateQuote(repository),
            GetFavoritesQuotes(repository),
            GetAllQuotes(repository)
        )
        viewModel = MainViewModel(interactors)
    }

    @Test
    fun getQuote_returnsNotNull() {
        // WHEN getting a random quote
        viewModel.getRandomQuote()

        // THEN assert quote is not null
        val quote = viewModel.quote.getOrAwaitValue()
        assertThat(quote, not(IsNull()))
    }

    @Test
    fun addToFavorite_returnsTrue() {
        // WHEN getting a random quote and update favorite
        viewModel.getRandomQuote()
        viewModel.updateFavorite()

        // THEN assert favorite is false
        val quote = viewModel.isFavorite.getOrAwaitValue()
        assertThat(quote, `is`(true))
    }

    @Test
    fun removeFromFavorite_returnsFalse() {
        // WHEN getting a random quote
        viewModel.getRandomQuote()
        //  And add it favorite
        viewModel.updateFavorite()
        //  And remove it from favorite
        viewModel.updateFavorite()

        // THEN assert favorite is false
        val quote = viewModel.isFavorite.getOrAwaitValue()
        assertThat(quote, `is`(false))
    }
}