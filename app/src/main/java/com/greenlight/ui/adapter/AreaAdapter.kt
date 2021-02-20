package com.greenlight.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Area
import com.greenlight.ui.interfaces.OnAreaClicked
import com.greenlight.ui.view.viewholder.AreaViewHolder


class AreaAdapter(private val listener: OnAreaClicked) : RecyclerView.Adapter<AreaViewHolder>() {

    var areas: List<Area>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = areas?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return AreaViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val area: Area? = areas?.get(position)!!
        if (area != null) {
            val viewHolder = holder as AreaViewHolder
            viewHolder.bindNowShowingData(area)
        } else {
            notifyItemRemoved(position)
        }
    }

}
