package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.DataModels.BestSellerModel
import com.example.dunzoclone.DataModels.Products
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.BestSellerViewHolder

class BestSellerAdapter(private val bestSellerlist: List<Products>) : RecyclerView.Adapter<BestSellerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_bestsell,parent,false)
        return BestSellerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
      val bestSeller : Products = bestSellerlist[position]
        holder.setBestSellerData(bestSeller)
    }

    override fun getItemCount(): Int {
    return bestSellerlist.size
    }
}