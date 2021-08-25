package com.example.dunzoclone.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.BestSellerModel
import kotlinx.android.synthetic.main.item_layout_bestsell.view.*

class BestSellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setBestSellerData(bestSellerlist : BestSellerModel){
        itemView.apply {
            Glide.with(ivBestproduct).load(bestSellerlist.pro_image_url).into(ivBestproduct)
            tvBestProductname.text = bestSellerlist.pro_name
            tvBestProdPrise.text = bestSellerlist.pro_price
            tvBestProdQty.text = bestSellerlist.pro_quantity
        }

    }
}