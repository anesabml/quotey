package com.anesabml.quotey.domain.usecase

import com.anesabml.quotey.data.IQuoteRepository
import com.anesabml.quotey.domain.model.Quote

class UpdateQuote(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke(quote: Quote) = quoteRepository.updateQuote(quote)
}