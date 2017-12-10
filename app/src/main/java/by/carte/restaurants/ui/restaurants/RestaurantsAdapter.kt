package by.carte.restaurants.ui.restaurants

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.carte.restaurants.R
import by.carte.restaurants.utils.inflate

class RestaurantsAdapter(private val restaurantsList: MutableList<String>) :
        RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RestaurantViewHolder(parent.inflate(R.layout.item_restaurant))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
            holder.bind(restaurantsList[position], callback)

    override fun getItemCount() = restaurantsList.size

    fun setRestaurants(restaurantItemList: List<String>) {
        restaurantsList.clear()
        restaurantsList.addAll(restaurantItemList)
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String, callback: Callback?) {
            itemView.findViewById<TextView>(R.id.text_title).text = item

            itemView.setOnClickListener { callback?.onItemClicked(item) }
        }
    }

    interface Callback {

        fun onItemClicked(item: String)
    }
}
