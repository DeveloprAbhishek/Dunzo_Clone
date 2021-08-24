package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.DataModels.Store_list_Model
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.StoreViewHolder

class StoreListAdapter(private val storeList: List<Store_list_Model>) :
    RecyclerView.Adapter<StoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.store_item_layout, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store: Store_list_Model = storeList[position]
        holder.setData(store)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }
}