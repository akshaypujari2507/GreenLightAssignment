package com.greenlight.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Region
import com.greenlight.ui.interfaces.OnRegionClicked
import com.greenlight.ui.view.viewholder.RegionViewHolder


class RegionAdapter(private val listener: OnRegionClicked) :
    RecyclerView.Adapter<RegionViewHolder>() {

    var regions: List<Region>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = regions?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return RegionViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        val region: Region? = regions?.get(position)!!
        if (region != null) {
            val viewHolder = holder as RegionViewHolder
            viewHolder.bindNowShowingData(region)
        } else {
            notifyItemRemoved(position)
        }
    }

}
