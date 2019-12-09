package com.anesabml.quotey.framework

import com.anesabml.quotey.core.interactors.*

data class Interactors(
    val getRandomQuote: GetRandomQuote,
    val addQuote: AddQuote,
    val addQuoteToFavorite: AddQuoteToFavorite,
    val removeQuoteFromFavorite: RemoveQuoteFromFavorite,
    val getFavoritesQuotes: GetFavoritesQuotes,
    val getAllQuotes: GetAllQuotes
)