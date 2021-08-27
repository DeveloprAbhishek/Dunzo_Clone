package com.example.dunzoclone.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.DataModels.SearchModel
import com.example.dunzoclone.R
import kotlinx.android.synthetic.main.search_item_layout.view.*

class SearchAdapter(var dataList: ArrayList<SearchModel>, val context: Context) :
    RecyclerView.Adapter<SearchAdapter.viewHolder>() {
    var backupList = ArrayList<SearchModel>()

    init {
        backupList = dataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item: SearchModel = dataList[position]
        holder.setData(item)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var filteredList = ArrayList<SearchModel>()
                val keyword = constraint.toString()
                if (keyword.isEmpty()) {
                    filteredList = backupList
                } else {

                    for (obj in backupList) {
                        if (obj.product_name.lowercase().contains(keyword.lowercase()) ||
                            obj.product_desc.lowercase().contains(keyword.lowercase())
                        ) {
                            filteredList.add(obj)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataList = results?.values as ArrayList<SearchModel>
                notifyDataSetChanged()
            }

        }
    }


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(model: SearchModel) {
            itemView.tvProductNameBySearch.text = model.product_name
            itemView.tvProductDescBySearch.text = model.product_desc
            itemView.tvQuantitySelectorBySearch.text = model.quantity
            itemView.tvProductPriceBySearch.text = model.price
        }
    }
}





