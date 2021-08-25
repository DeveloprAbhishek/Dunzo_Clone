package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.ClickLitener.StoreItemClickListener
import com.example.dunzoclone.DataModels.StoreListModel
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.StoreViewHolder
import java.util.ArrayList

class StoreListAdapter(private val storeList: ArrayList<StoreListModel>, private var storeItemClickListener: StoreItemClickListener) :
    RecyclerView.Adapter<StoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item_layout, parent, false)
        return StoreViewHolder(view, storeItemClickListener)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store: StoreListModel = storeList[position]
        holder.setStoreData(store, position)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }
}