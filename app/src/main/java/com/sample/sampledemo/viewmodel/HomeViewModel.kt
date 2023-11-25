package com.sample.sampledemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.internal.LinkedTreeMap
import com.sample.sampledemo.model.HomeScreenApiResponseModel
import com.sample.sampledemo.repository.api.repositories.HomeRepository
import com.sample.sampledemo.repository.local.roomdb.preference.AppPreference
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val homeRepository = HomeRepository()
    var homeProductList: MutableStateFlow<HomeScreenApiResponseModel> = MutableStateFlow(HomeScreenApiResponseModel())

    fun getHomeProducts() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("HomeViewModel", "getHomeProducts: ${throwable.message}")
        }
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = homeRepository.getHomeProducts()
            CoroutineScope(Dispatchers.Main).launch {
                response?.let {
                    AppPreference.shared.homeScreenData = it
                    homeProductList.value = it
                }
            }
        }
    }
}