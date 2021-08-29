package com.example.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dunzoclone.DataModels.Products
import kotlinx.android.synthetic.main.order_item_layout.view.*

class OrderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    fun setOrderData(orderData: Products) {
        itemView.apply {
            Glide.with(ivImageProduct).load(orderData.image).into(ivImageProduct)

            tvCategoryNameOrder.text = orderData.category
            tvProductQuantityOrder.text = orderData.quantity
            tvPriceOrder.text = orderData.price
        }
    }
}