package com.sample.sampledemo.repository.api.repositories

import com.sample.sampledemo.model.HomeScreenApiResponseModel
import com.sample.sampledemo.repository.api.restservice.RestService
import com.sample.sampledemo.repository.local.roomdb.preference.AppPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository {
    companion object {
        val restService = RestService()
    }

    suspend fun getHomeProducts(): HomeScreenApiResponseModel? {
        return withContext(Dispatchers.IO) {
            val onlineData = restService.getHomeProducts()
            return@withContext onlineData ?: AppPreference.shared.homeScreenData
        }
    }
}