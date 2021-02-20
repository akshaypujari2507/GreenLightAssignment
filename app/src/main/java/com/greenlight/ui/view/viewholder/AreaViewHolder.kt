package com.greenlight.ui.view.viewholder

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Area
import com.greenlight.ui.interfaces.OnAreaClicked


class AreaViewHolder(
    itemView: View?,
    val listener: OnAreaClicked
) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {

    var tv_Item: TextView
    private var mArea: Area? = null

    init {
        tv_Item = itemView!!.findViewById(R.id.tv_Item)

        itemView.setOnClickListener(this)

    }

    fun bindNowShowingData(mArea: Area?) {
        if (mArea == null) {
            return
        } else {

            this.mArea = mArea

            tv_Item.setText(mArea.area)
            tv_Item.setPaintFlags(tv_Item.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        }
    }

    override fun onClick(p0: View?) {
        val position: Int = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClicked(mArea!!)
        }
    }

}