package by.carte.restaurants.data.remote.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

@SuppressLint("ParcelCreator")
data class CitiesResponse(
        @SerializedName("data")
        val data: List<CityDataItem>
) : AutoParcelable
