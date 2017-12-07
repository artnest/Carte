package by.carte.restaurants.data.model.api

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
        @SerializedName("status")
        val statusCode: String,
        @SerializedName("data")
        val data: String
)
