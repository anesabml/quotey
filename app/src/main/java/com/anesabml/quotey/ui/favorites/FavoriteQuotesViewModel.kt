package com.anesabml.quotey.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anesabml.quotey.R
import com.anesabml.quotey.domain.model.Quote
import com.anesabml.quotey.domain.Interactors
import com.anesabml.quotey.ui.base.QuoteyViewModel
import kotlinx.coroutines.launch

class FavoriteQuotesViewModel(private val interactors: Interactors) :
    QuoteyViewModel() {

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>> = _quotes

    private val _errorText = MutableLiveData<Int>()
    val errorText: LiveData<Int> = _errorText

    init {
        getFavoritesQuotes()
    }

    private fun getFavoritesQuotes() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val result = interactors.getFavoritesQuotes.invoke()
                if (result.isEmpty()) {
                    _errorText.value = R.string.no_favorites_yet
                } else {
                    _quotes.postValue(interactors.getFavoritesQuotes.invoke())
                }
            } catch (error: Throwable) {
                Log.e("FavoriteQuotesViewModel", error.message ?: "Error trying to get favorites")
                _errorText.value = R.string.error_getting_favorites
            }
            isLoading.postValue(false)
        }
    }
}
