package com.greenlight.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Zone
import com.greenlight.ui.interfaces.OnZoneClicked
import com.greenlight.ui.view.viewholder.ZoneViewHolder


class ZoneAdapter(private val listener: OnZoneClicked) : RecyclerView.Adapter<ZoneViewHolder>() {

    var zones: List<Zone>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = zones?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return ZoneViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ZoneViewHolder, position: Int) {
        val zone: Zone? = zones?.get(position)!!
        if (zone != null) {
            val viewHolder = holder as ZoneViewHolder
            viewHolder.bindNowShowingData(zone)
        } else {
            notifyItemRemoved(position)
        }
    }

}
