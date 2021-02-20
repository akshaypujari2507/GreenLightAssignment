package com.greenlight.ui.view.viewholder

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Region
import com.greenlight.ui.interfaces.OnRegionClicked


class RegionViewHolder(
    itemView: View?,
    val listener: OnRegionClicked
) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {

    var tv_Item: TextView
    private var mRegion: Region? = null

    init {
        tv_Item = itemView!!.findViewById(R.id.tv_Item)

        itemView.setOnClickListener(this)

    }

    fun bindNowShowingData(mRegion: Region?) {
        if (mRegion == null) {
            return
        } else {

            this.mRegion = mRegion

            tv_Item.setText(mRegion.region)
            tv_Item.setPaintFlags(tv_Item.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        }
    }

    override fun onClick(p0: View?) {
        val position: Int = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClicked(mRegion!!)
        }
    }

}