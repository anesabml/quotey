package com.anesabml.quotey.domain.usecase

import com.anesabml.quotey.data.IQuoteRepository

class GetAllQuotes(private val quoteRepository: IQuoteRepository) {
    suspend operator fun invoke() = quoteRepository.getAll()
}