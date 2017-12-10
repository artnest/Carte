package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class RegionDataItem(
        @SerializedName("name")
        val name: String,
        @SerializedName("id")
        val id: Int
)
