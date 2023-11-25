package com.sample.sampledemo.model

import com.google.gson.annotations.SerializedName


class HomeScreenApiResponseModel : ArrayList<HomeScreenApiResponseModel.HomeScreenItem>(){
    data class HomeScreenItem(
        var statusCode: Int = 0,
        @SerializedName("contents")
        val contents: List<Content?>? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("image_url")
        val imageUrl: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("type")
        val type: String? = null
    ) : java.io.Serializable {
        data class Content(
            @SerializedName("actual_price")
            val actualPrice: String? = null,
            @SerializedName("discount")
            val discount: String? = null,
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("offer_price")
            val offerPrice: String? = null,
            @SerializedName("product_image")
            val productImage: String? = null,
            @SerializedName("product_name")
            val productName: String? = null,
            @SerializedName("product_rating")
            val productRating: Int? = null,
            @SerializedName("sku")
            val sku: String? = null,
            @SerializedName("title")
            val title: String? = null
        ) : java.io.Serializable
    }
}