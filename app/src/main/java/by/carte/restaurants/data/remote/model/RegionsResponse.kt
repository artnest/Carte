package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class RegionsResponse(
        @SerializedName("data")
        val data: List<RegionDataItem> = arrayListOf()
)
