package com.anesabml.quotey.core.domain.usecase

import com.anesabml.quotey.core.data.IQuoteRepository

class GetRandomQuote(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke() = quoteRepository.getQod()
}