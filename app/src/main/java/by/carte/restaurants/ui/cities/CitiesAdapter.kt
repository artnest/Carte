package by.carte.restaurants.ui.cities

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.carte.restaurants.R
import by.carte.restaurants.data.model.api.CitiesResponse
import by.carte.restaurants.utils.inflate

class CitiesAdapter(private val citiesResponseList: MutableList<CitiesResponse>) :
        RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    // TODO: add listener to items as lambda (from fragment)
    // TODO: use callback to fragment as item click listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CityViewHolder(parent.inflate(R.layout.item_city))

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
            holder.bind(citiesResponseList[position])

    override fun getItemCount() = citiesResponseList.size

    fun addItems(citiesList: List<CitiesResponse>) {
        citiesResponseList.addAll(citiesList)
        notifyDataSetChanged()
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CitiesResponse) {
            itemView.findViewById<TextView>(R.id.text_title).text = item.data
        }
    }
}