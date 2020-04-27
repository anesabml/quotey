package com.anesabml.quotey.domain

import com.anesabml.quotey.domain.usecase.*

data class Interactors(
    val getRandomQuote: GetRandomQuote,
    val addQuote: AddQuote,
    val updateQuote: UpdateQuote,
    val getFavoritesQuotes: GetFavoritesQuotes,
    val getAllQuotes: GetAllQuotes
)