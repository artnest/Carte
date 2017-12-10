package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class CitiesResponse(
        @SerializedName("data")
        val data: List<CityDataItem>
)
