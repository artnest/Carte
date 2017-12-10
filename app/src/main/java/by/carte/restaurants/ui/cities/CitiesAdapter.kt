package by.carte.restaurants.ui.cities

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.utils.inflate

class CitiesAdapter(private val citiesList: MutableList<CityDataItem>) :
        RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CityViewHolder(parent.inflate(R.layout.item_city))

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
            holder.bind(citiesList[position], callback)

    override fun getItemCount() = citiesList.size

    fun setCities(cityItemList: List<CityDataItem>) {
        citiesList.clear()
        citiesList.addAll(cityItemList)
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CityDataItem, callback: Callback?) {
            itemView.findViewById<TextView>(R.id.text_title).text = item.name

            itemView.setOnClickListener { callback?.onItemClicked(item) }
        }
    }

    interface Callback {

        fun onItemClicked(item: CityDataItem)
    }
}
