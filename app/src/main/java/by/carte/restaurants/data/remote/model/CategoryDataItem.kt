package by.carte.restaurants.data.remote.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class CategoryDataItem(
        @SerializedName("restaurant_id")
        val restaurantId: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("id")
        val id: Int
) : AutoParcelable
