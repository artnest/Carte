package by.carte.restaurants.ui.restaurants

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.RestaurantDataItem
import by.carte.restaurants.utils.inflate
import by.carte.restaurants.utils.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_restaurant.*

class RestaurantsAdapter(private val restaurantsList: MutableList<RestaurantDataItem>) :
        RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RestaurantViewHolder(parent.inflate(R.layout.item_restaurant))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) =
            holder.bind(restaurantsList[position], callback)

    override fun getItemCount() = restaurantsList.size

    fun setRestaurants(restaurantItemList: List<RestaurantDataItem>) {
        restaurantsList.clear()
        restaurantsList.addAll(restaurantItemList)
    }

    class RestaurantViewHolder(override val containerView: View) : ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: RestaurantDataItem, callback: Callback?) {
            image_restaurant.loadImage(item.logo)
            text_restaurant_name.text = item.name
            text_address.text = item.address

            button_show_more.setOnClickListener { callback?.onDetailsButtonClicked(item) }
            button_show_map.setOnClickListener { callback?.onMapButtonClicked(item) }
        }
    }

    interface Callback {

        fun onDetailsButtonClicked(restaurantItem: RestaurantDataItem)

        fun onMapButtonClicked(restaurantItem: RestaurantDataItem)
    }
}
