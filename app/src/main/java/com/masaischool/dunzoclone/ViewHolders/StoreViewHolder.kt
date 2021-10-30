package com.masaischool.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masaischool.dunzoclone.ClickLitener.StoreItemClickListener
import com.masaischool.dunzoclone.DataModels.Store
import kotlinx.android.synthetic.main.store_item_layout.view.*

class StoreViewHolder(view: View, private var storeItemClickListener: StoreItemClickListener) : RecyclerView.ViewHolder(view) {

    fun setStoreData(storeList: Store, position: Int) {
        itemView.apply {
            tvStoreName.text = storeList.store_name
            tvAddress.text = storeList.address
            Glide.with(ivProductImage).load(storeList.store_image).into(ivProductImage)
        }

        itemView.storeCardView.setOnClickListener {
            storeItemClickListener.onItemClickListener(storeList, position)
        }
    }
}