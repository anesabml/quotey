package com.anesabml.quotey.core.domain.usecase

import com.anesabml.quotey.core.data.IQuoteRepository

class GetAllQuotes(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke() = quoteRepository.getAll()
}