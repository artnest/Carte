package by.carte.restaurants.data.remote.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class RestaurantDataItem(
        @SerializedName("average")
        val average: String,
        @SerializedName("bookable")
        val bookable: Boolean,
        @SerializedName("images")
        val images: List<String> = arrayListOf(),
        @SerializedName("address")
        val address: String,
        @SerializedName("phone")
        val phone: String,
        @SerializedName("orderable")
        val orderable: Boolean,
        @field:SerializedName("name")
        val name: String,
        @SerializedName("logo")
        val logo: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("worktime")
        val worktime: String,
        @SerializedName("city_id")
        val cityId: Int
) : AutoParcelable
