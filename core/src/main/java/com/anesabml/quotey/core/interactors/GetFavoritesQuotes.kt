package com.anesabml.quotey.core.interactors

import com.anesabml.quotey.core.data.IQuoteRepository
import com.anesabml.quotey.core.data.QuoteRepository

class GetFavoritesQuotes(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke() = quoteRepository.getFavorites()
}