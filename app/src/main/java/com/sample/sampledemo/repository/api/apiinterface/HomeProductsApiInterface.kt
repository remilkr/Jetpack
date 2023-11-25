package com.sample.sampledemo.repository.api.apiinterface

import com.sample.sampledemo.model.HomeScreenApiResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeProductsApiInterface {
    @Headers("Content-Type: application/json")
    @GET("/api/Todo")
    suspend fun getProducts(
    ): Response<HomeScreenApiResponseModel>

}