package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class CityDataItem(
        @SerializedName("name")
        val name: String,
        @SerializedName("region_id")
        val regionId: Int,
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("id")
        val id: Int
)