package com.anesabml.quotey.framework

import com.anesabml.quotey.core.domain.usecase.*

data class Interactors(
    val getRandomQuote: GetRandomQuote,
    val addQuote: AddQuote,
    val updateQuote: UpdateQuote,
    val getFavoritesQuotes: GetFavoritesQuotes,
    val getAllQuotes: GetAllQuotes
)