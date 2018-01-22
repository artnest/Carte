package by.carte.restaurants.ui.regions

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_region.*

class RegionsAdapter(private val regionsList: MutableList<RegionDataItem>) :
        RecyclerView.Adapter<RegionsAdapter.RegionViewHolder>() {

    var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RegionViewHolder(parent.inflate(R.layout.item_region))

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) =
            holder.bind(regionsList[position], callback)

    override fun getItemCount() = regionsList.size

    fun setRegions(regionItemList: List<RegionDataItem>) {
        regionsList.clear()
        regionsList.addAll(regionItemList)
    }

    class RegionViewHolder(override val containerView: View) : ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: RegionDataItem, callback: Callback?) {
            text_region_name.text = item.name

            containerView.setOnClickListener { callback?.onItemClicked(item) }
        }
    }

    interface Callback {

        fun onItemClicked(item: RegionDataItem)
    }
}
