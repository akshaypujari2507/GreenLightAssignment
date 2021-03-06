package com.greenlight.ui.view.viewholder

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Zone
import com.greenlight.ui.interfaces.OnZoneClicked


class ZoneViewHolder(
    itemView: View?,
    val listener: OnZoneClicked
) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {

    var tv_Item: TextView
    private var mZone: Zone? = null

    init {
        tv_Item = itemView!!.findViewById(R.id.tv_Item)

        itemView.setOnClickListener(this)

    }

    fun bindNowShowingData(mZone: Zone?) {
        if (mZone == null) {
            return
        } else {

            this.mZone = mZone

            tv_Item.setText(mZone.zone)
            tv_Item.setPaintFlags(tv_Item.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        }
    }

    override fun onClick(p0: View?) {
        val position: Int = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClicked(mZone!!)
        }
    }

}