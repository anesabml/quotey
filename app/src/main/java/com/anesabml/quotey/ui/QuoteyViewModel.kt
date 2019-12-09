package com.anesabml.quotey.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anesabml.quotey.framework.Interactors
import kotlinx.coroutines.CoroutineExceptionHandler

open class QuoteyViewModel(protected val interactors: Interactors) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        isLoading.postValue(false)
        exception.message?.let {
            Log.e("BaseViewModel", it)
            error.postValue(it)
        }
    }

}
