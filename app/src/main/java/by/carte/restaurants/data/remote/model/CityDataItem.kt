package by.carte.restaurants.data.remote.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class CityDataItem(
        @SerializedName("name")
        val name: String,
        @SerializedName("region_id")
        val regionId: Int,
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("id")
        val id: Int
) : AutoParcelable