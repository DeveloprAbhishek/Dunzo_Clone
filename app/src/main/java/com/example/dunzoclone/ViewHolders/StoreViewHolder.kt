package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.ClickLitener.StoreItemClickListener
import com.example.dunzoclone.DataModels.StoreListModel
import kotlinx.android.synthetic.main.store_item_layout.view.*

class StoreViewHolder(view: View, private var storeItemClickListener: StoreItemClickListener) : RecyclerView.ViewHolder(view) {

    fun setStoreData(storeList: StoreListModel, position: Int) {
        itemView.apply {
            tvStoreName.text = storeList.store_name
            tvAddress.text = storeList.address
            tvDistance.text = storeList.distance
            tvTime.text = storeList.time
            Glide.with(ivProductImage).load(storeList.image).into(ivProductImage)
        }

        itemView.storeCardView.setOnClickListener {
            storeItemClickListener.onItemClickListener(storeList, position)
        }
    }
}