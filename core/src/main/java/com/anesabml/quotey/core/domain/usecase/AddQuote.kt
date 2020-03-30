package com.anesabml.quotey.core.domain.usecase

import com.anesabml.quotey.core.data.IQuoteRepository
import com.anesabml.quotey.core.domain.model.Quote

class AddQuote(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke(quote: Quote) = quoteRepository.addQuote(quote)
}