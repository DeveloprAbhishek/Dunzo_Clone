package com.masaischool.dunzoclone.ViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masaischool.dunzoclone.DataModels.Products
import kotlinx.android.synthetic.main.order_item_layout.view.*

class OrderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    fun setOrderData(orderData: Products) {
        itemView.apply {
            tvProductNameOrder.text = orderData.name
            tvProductQuantityOrder.text = "x"+ orderData.quantity
        }
    }
}