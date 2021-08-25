package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.DataModels.Store_list_Model
import kotlinx.android.synthetic.main.store_item_layout.view.*

class StoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setStoreData(storeList: Store_list_Model) {
        itemView.apply {
            tvStoreName.text = storeList.store_name
            tvAddress.text = storeList.address
            tvDistance.text = storeList.distance
            tvTime.text = storeList.time

            Glide.with(ivProductImage).load(storeList.image_url).into(ivProductImage)
        }
    }
}