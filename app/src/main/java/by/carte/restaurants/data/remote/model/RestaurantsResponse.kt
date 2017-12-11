package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
        @SerializedName("data")
        val data: List<RestaurantDataItem> = arrayListOf()
)
