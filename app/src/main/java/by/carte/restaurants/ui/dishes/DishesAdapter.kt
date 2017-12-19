package by.carte.restaurants.ui.dishes

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.DishDataItem
import by.carte.restaurants.utils.inflate
import by.carte.restaurants.utils.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_dish.*

class DishesAdapter(private val dishesList: MutableList<DishDataItem>) :
        RecyclerView.Adapter<DishesAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            DishViewHolder(parent.inflate(R.layout.item_dish))

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) =
            holder.bind(dishesList[position])

    override fun getItemCount() = dishesList.size

    fun setDishes(dishesItemList: List<DishDataItem>) {
        dishesList.clear()
        dishesList.addAll(dishesItemList)
    }

    class DishViewHolder(override val containerView: View) : ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: DishDataItem) {
            text_dish_name.text = item.name
            text_description.text = item.composition
            image_dish.loadImage(item.image)
            text_price.text = item.price
            text_weight.text = item.weight
        }
    }
}
