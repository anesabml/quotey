package com.anesabml.quotey.domain.usecase

import com.anesabml.quotey.data.IQuoteRepository

class GetFavoritesQuotes(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke() = quoteRepository.getFavorites()
}