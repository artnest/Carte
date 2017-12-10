package by.carte.restaurants.ui.cities

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import by.carte.restaurants.R
import by.carte.restaurants.data.remote.model.RegionDataItem
import by.carte.restaurants.utils.inflate

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

    class RegionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: RegionDataItem, callback: Callback?) {
            itemView.findViewById<TextView>(R.id.text_title).text = item.name

            itemView.setOnClickListener { callback?.onItemClicked(item) }
        }
    }

    interface Callback {

        fun onItemClicked(item: RegionDataItem)
    }
}
