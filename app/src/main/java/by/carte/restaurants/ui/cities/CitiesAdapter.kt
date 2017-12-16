package by.carte.restaurants.ui.cities

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CityDataItem
import by.carte.restaurants.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_city.*

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

    class CityViewHolder(override val containerView: View) : ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: CityDataItem, callback: Callback?) {
            text_city_name.text = item.name

            if (item.active) {
                containerView.setOnClickListener { callback?.onItemClicked(item) }
            } else {
                text_city_name.isEnabled = false
            }
        }
    }

    interface Callback {

        fun onItemClicked(item: CityDataItem)
    }
}
