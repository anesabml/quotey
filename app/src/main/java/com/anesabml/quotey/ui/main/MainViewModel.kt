package com.anesabml.quotey.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.framework.Interactors
import com.anesabml.quotey.ui.QuoteyViewModel
import kotlinx.coroutines.launch

class MainViewModel(interactors: Interactors) :
    QuoteyViewModel(interactors) {

    val quote = MutableLiveData<Quote>()
    val isFavorite = MutableLiveData<Boolean>(false)

    fun getRandomQuote() {
        viewModelScope.launch(coroutineExceptionHandler) {
            isLoading.postValue(true)
            // Get the quote of the day 'Qod'
            val qod = interactors.getRandomQuote.invoke()
            // Add quote to db
            interactors.addQuote(qod)
            // Set the quote
            setQuote(qod)
            isLoading.postValue(false)
        }
    }

    fun getLastQuote() {
        viewModelScope.launch(coroutineExceptionHandler) {
            isLoading.postValue(true)
            // Get the last quote from db
            val allQuotes = interactors.getAllQuotes.invoke()
            allQuotes.firstOrNull()?.let {
                // Set the quote
                setQuote(it)
            }
            isLoading.postValue(false)
        }
    }

    fun setQuote(newQuote: Quote) {
        quote.postValue(newQuote)
    }

    fun updateFavorite() {
        viewModelScope.launch(coroutineExceptionHandler) {
            quote.value?.let {
                isLoading.postValue(true)
                if (isFavorite.value!!) {
                    interactors.removeQuoteFromFavorite.invoke(it)
                    isFavorite.postValue(false)
                } else {
                    interactors.addQuoteToFavorite.invoke(it)
                    isFavorite.postValue(true)
                }
                isLoading.postValue(false)
            }

        }
    }
}
