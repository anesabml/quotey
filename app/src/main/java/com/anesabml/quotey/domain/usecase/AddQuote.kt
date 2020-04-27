package com.anesabml.quotey.domain.usecase

import com.anesabml.quotey.data.IQuoteRepository
import com.anesabml.quotey.domain.model.Quote

class AddQuote(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke(quote: Quote) = quoteRepository.addQuote(quote)
}