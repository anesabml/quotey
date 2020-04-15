package com.anesabml.quotey.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.anesabml.quotey.R
import com.anesabml.quotey.domain.Interactors
import com.anesabml.quotey.domain.model.Quote
import com.anesabml.quotey.ui.base.QuoteyViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val interactors: Interactors) :
    QuoteyViewModel() {

    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    val isFavorite: LiveData<Boolean> = Transformations.map(_quote) {
        it.isFavorite
    }

    private val _errorText = MutableLiveData<Int>()
    val errorText: LiveData<Int> = _errorText

    init {
        getRandomQuote()
    }

    fun getRandomQuote() {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                // Get the quote of the day 'Qod'
                val qod = interactors.getRandomQuote.invoke()
                // Add quote to db
                interactors.addQuote(qod)
                // Set the quote
                _quote.postValue(qod)
            } catch (error: Throwable) {
                Log.e(
                    "MainViewModel",
                    error.message ?: "Error trying to get quote"
                )
                _errorText.value = R.string.error_getting_quote
            } finally {
                isLoading.postValue(false)
            }
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
                    _quote.postValue(it)
                }
            } catch (error: Throwable) {
                Log.e(
                    "MainViewModel",
                    error.message ?: "Error trying to get quote"
                )
                _errorText.value = R.string.error_getting_quote
            } finally {
                isLoading.postValue(false)
            }
        }
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
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}

private fun <T> MutableLiveData<T>.notifyObservers() {
    this.value = this.value
}
