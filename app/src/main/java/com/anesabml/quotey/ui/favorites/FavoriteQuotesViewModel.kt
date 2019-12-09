package com.anesabml.quotey.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anesabml.quotey.core.domain.Quote
import com.anesabml.quotey.framework.Interactors
import com.anesabml.quotey.ui.QuoteyViewModel
import kotlinx.coroutines.launch

class FavoriteQuotesViewModel(interactors: Interactors) :
    QuoteyViewModel(interactors) {

    val quotes = MutableLiveData<List<Quote>>()

    fun getFavoritesQuotes() {
        viewModelScope.launch(coroutineExceptionHandler) {
            isLoading.postValue(true)
            quotes.postValue(interactors.getFavoritesQuotes.invoke())
            isLoading.postValue(false)
        }
    }


}
