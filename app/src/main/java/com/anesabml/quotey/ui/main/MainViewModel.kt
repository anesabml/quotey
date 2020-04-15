package com.anesabml.quotey.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.anesabml.quotey.R
import com.anesabml.quotey.domain.model.Quote
import com.anesabml.quotey.domain.Interactors
import com.anesabml.quotey.ui.base.QuoteyViewModel
import kotlinx.coroutines.launch

class MainViewModel(interactors: Interactors) :
    QuoteyViewModel(interactors) {

    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    val isFavorite: LiveData<Boolean> = Transformations.map(_quote) {
        it.isFavorite
    }

    private val _errorText = MutableLiveData<Int>()
    val errorText: LiveData<Int> = _errorText

    fun getRandomQuote() {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                // Get the quote of the day 'Qod'
                val qod = interactors.getRandomQuote.invoke()
                // Add quote to db
                interactors.addQuote(qod)
                // Set the quote
                setQuote(qod)
            } catch (error: Throwable) {
                Log.e(
                    "MainViewModel",
                    error.message ?: "Error trying to get quote"
                )
                _errorText.value = R.string.error_getting_quote
            }
            isLoading.postValue(false)
        }
    }

    fun getLastQuote() {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                // Get the last quote from db
                val allQuotes = interactors.getAllQuotes.invoke()
                allQuotes.firstOrNull()?.let {
                    // Set the quote
                    setQuote(it)
                }
            } catch (error: Throwable) {
                Log.e(
                    "MainViewModel",
                    error.message ?: "Error trying to get quote"
                )
                _errorText.value = R.string.error_getting_quote
            }
            isLoading.postValue(false)
        }
    }

    private fun setQuote(newQuote: Quote) {
        _quote.postValue(newQuote)
    }

    fun updateFavorite() {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                quote.value?.let {
                    if (it.isFavorite) {
                        it.isFavorite = false
                        interactors.updateQuote.invoke(it)
                        _quote.notifyObservers()
                    } else {
                        it.isFavorite = true
                        interactors.updateQuote.invoke(it)
                        _quote.notifyObservers()
                    }
                }
            } catch (error: Throwable) {
                Log.e(
                    "MainViewModel",
                    error.message ?: "Error trying to update favorites"
                )
                _errorText.value = R.string.error_updating_favorites
            }
            isLoading.postValue(false)
        }
    }

    fun start() {
        getRandomQuote()
    }
}

private fun <T> MutableLiveData<T>.notifyObservers() {
    this.value = this.value
}
