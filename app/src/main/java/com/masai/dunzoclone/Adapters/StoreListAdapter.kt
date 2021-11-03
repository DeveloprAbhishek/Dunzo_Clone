package com.masai.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.dunzoclone.ClickLitener.StoreItemClickListener
import com.masai.dunzoclone.DataModels.Store
import com.masai.dunzoclone.R
import com.masai.dunzoclone.ViewHolders.StoreViewHolder
import java.util.ArrayList

class StoreListAdapter(private val storeList: ArrayList<Store>, private var storeItemClickListener: StoreItemClickListener) :
    RecyclerView.Adapter<StoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.store_item_layout, parent, false)
        return StoreViewHolder(view, storeItemClickListener)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store: Store = storeList[position]
        holder.setStoreData(store, position)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }
}