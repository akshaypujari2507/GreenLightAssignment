package com.greenlight.ui.view.viewholder

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Employee
import com.greenlight.ui.interfaces.OnEmployeeClicked


class EmployeeViewHolder(
    itemView: View?, val context: Context,
    val listener: OnEmployeeClicked
) : RecyclerView.ViewHolder(itemView!!), View.OnClickListener {

    var tv_Item: TextView
    private var mEmployee: Employee? = null

    init {
        tv_Item = itemView!!.findViewById(R.id.tv_Item)

        itemView.setOnClickListener(this)

    }

    fun bindNowShowingData(mEmployee: Employee?) {
        if (mEmployee == null) {
            return
        } else {

            this.mEmployee = mEmployee

            tv_Item.setText(mEmployee.name)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv_Item.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
            } else {
                tv_Item.setTextColor(context.getResources().getColor(R.color.colorBlack));
            }

        }
    }

    override fun onClick(p0: View?) {
        val position: Int = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onItemClicked(mEmployee!!)
        }
    }

}