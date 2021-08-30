package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.DataModels.BestSellerModel
import com.example.dunzoclone.DataModels.Products
import kotlinx.android.synthetic.main.item_layout_bestsell.view.*

class BestSellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setBestSellerData(bestSellerlist : Products){
        itemView.apply {
            Glide.with(ivBestproduct).load(bestSellerlist.image).into(ivBestproduct)
            tvBestProductname.text = bestSellerlist.name
            tvBestProdPrise.text = "₹" + bestSellerlist.price
            tvBestProdQty.text = bestSellerlist.quantity + " item"
        }

    }
}