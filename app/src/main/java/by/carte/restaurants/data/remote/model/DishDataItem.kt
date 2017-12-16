package by.carte.restaurants.data.remote.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class DishDataItem(
        @SerializedName("image")
        val image: String,
        @SerializedName("category_id")
        val categoryId: Int,
        @SerializedName("composition")
        val composition: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("weight")
        val weight: String,
        @SerializedName("id")
        val id: Int
) : AutoParcelable
