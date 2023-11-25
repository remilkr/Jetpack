package com.sample.sampledemo.repository.api.restservice

import com.sample.sampledemo.model.HomeScreenApiResponseModel
import com.sample.sampledemo.repository.api.apiinterface.HomeProductsApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService {
    companion object {
        private const val BASE_URL = "https://64bfc2a60d8e251fd111630f.mockapi.io"
        fun getRetrofitExceptionErrorCode(error: Throwable): Int {
            return when (error) {
                is HttpException -> (error).code()
                else -> -1 //todo this is temp
            }
        }
    }

    private fun retrofit(addBearerTokenToHeader: Boolean = true): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }


    suspend fun getHomeProducts(): HomeScreenApiResponseModel? {
        return withContext(Dispatchers.IO) {
            val apiInterface = retrofit().create(HomeProductsApiInterface::class.java)
            var serverResponse: Response<HomeScreenApiResponseModel>? = null
            val statusCode = try {
                serverResponse = apiInterface.getProducts()
                serverResponse.body() ?: serverResponse.code()
            } catch (e: Exception) {
                e.printStackTrace()
                getRetrofitExceptionErrorCode(e)
            }
            serverResponse?.body()
        }
    }
}