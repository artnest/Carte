package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class DishesResponse(
        @SerializedName("data")
        val data: List<DishDataItem> = arrayListOf()
)
