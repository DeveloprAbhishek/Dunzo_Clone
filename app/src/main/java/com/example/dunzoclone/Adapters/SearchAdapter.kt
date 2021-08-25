//package com.example.dunzoclone.Activities
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.Filter
//import android.widget.Filterable
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dunzoclone.DataModels.Product_Model
//import com.example.dunzoclone.R
//import java.util.*
//
//class S_Adapter(dataList: ArrayList<Product_Model>, context: Context) :
//    RecyclerView.Adapter<SviewHolder>(), Filterable {
//    var dataList = ArrayList<Product_Model>()
//    lateinit var backupList: ArrayList<Product_Model>
//    var context: Context
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SviewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
//        return SviewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: SviewHolder, position: Int) {
//        val item = dataList[position]
//        holder.setData(item)
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    override fun getFilter(): Filter {
//        return filter
//    }
//
//    var filter: Filter = object : Filter() {
//        override fun performFiltering(keyword: CharSequence): FilterResults {
//            val filteredData = ArrayList<Product_Model>()
//            if (keyword.toString().isEmpty()) {
//                filteredData.addAll(backupList)
//            } else {
//                for (obj in backupList) {
//                    if (obj.product_name.lowercase().contains(keyword.toString().lowercase())
//                        || obj.product_desc.lowercase().contains(keyword.toString().lowercase())
//                    ) {
//                        filteredData.add(obj)
//                    }
//                }
//            }
//            val results = FilterResults()
//            results.values = filteredData
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence, results: FilterResults) {
//            dataList.clear()
//            dataList.addAll((results.values as ArrayList<Product_Model>))
//            notifyDataSetChanged()
//        }
//    }
//
//    init {
//        this.dataList = dataList
//        this.context = context
//        backupList = ArrayList(dataList)
//    }
//}