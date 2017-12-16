package by.carte.restaurants.data.remote.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
        @SerializedName("data")
        val data: List<CategoryDataItem> = arrayListOf()
)
