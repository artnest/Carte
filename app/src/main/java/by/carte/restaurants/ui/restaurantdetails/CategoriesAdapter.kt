package by.carte.restaurants.ui.restaurantdetails

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.CategoryDataItem
import by.carte.restaurants.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_category.*

class CategoriesAdapter(private val categoriesList: MutableList<CategoryDataItem>) :
        RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoryViewHolder(parent.inflate(R.layout.item_category))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
            holder.bind(categoriesList[position], callback)

    override fun getItemCount() = categoriesList.size

    fun setCategories(categoriesItemList: List<CategoryDataItem>) {
        categoriesList.clear()
        categoriesList.addAll(categoriesItemList)
    }

    class CategoryViewHolder(override val containerView: View) : ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: CategoryDataItem, callback: Callback?) {
            text_category_name.text = item.name

            containerView.setOnClickListener { callback?.onItemClicked(item) }
        }
    }

    interface Callback {

        fun onItemClicked(categoryItem: CategoryDataItem)
    }
}
