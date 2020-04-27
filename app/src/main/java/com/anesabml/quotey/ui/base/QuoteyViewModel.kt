package com.anesabml.quotey.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anesabml.quotey.domain.Interactors

open class QuoteyViewModel() : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

}
