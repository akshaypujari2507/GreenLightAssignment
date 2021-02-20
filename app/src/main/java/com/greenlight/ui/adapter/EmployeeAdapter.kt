package com.greenlight.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.greenlight.R
import com.greenlight.data.local.entities.Employee
import com.greenlight.ui.interfaces.OnEmployeeClicked
import com.greenlight.ui.view.viewholder.EmployeeViewHolder
import java.util.*


class EmployeeAdapter(private val listener: OnEmployeeClicked) :
    RecyclerView.Adapter<EmployeeViewHolder>(),
    Filterable {

    public var filteredList: MutableList<Employee> = arrayListOf()

    var employees: List<Employee>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = filteredList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return EmployeeViewHolder(view, parent.context, listener)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee: Employee? = filteredList?.get(position)!!
        if (employee != null) {
            val viewHolder = holder as EmployeeViewHolder
            viewHolder.bindNowShowingData(employee)
        } else {
            notifyItemRemoved(position)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filteredList.clear()
                if (charSearch.isEmpty()) {
                    filteredList.addAll(employees!!)
                } else {
                    for (row in employees!!) {
                        if (row.name!!.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            filteredList.add(row)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }

}
