package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.DataModels.BestSellerModel
import kotlinx.android.synthetic.main.item_layout_bestsell.view.*

class BestSellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setBestSellerData(bestSellerlist : BestSellerModel){
        itemView.apply {
            Glide.with(ivBestproduct).load(bestSellerlist.item_img_url).into(ivBestproduct)
            tvBestProductname.text = bestSellerlist.item_name
            tvBestProdPrise.text = bestSellerlist.item_price
            tvBestProdQty.text = bestSellerlist.item_quantity
        }

    }
}