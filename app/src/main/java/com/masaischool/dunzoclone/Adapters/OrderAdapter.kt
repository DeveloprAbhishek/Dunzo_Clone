package com.masaischool.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masaischool.dunzoclone.DataModels.Products
import com.masaischool.dunzoclone.R
import com.masaischool.dunzoclone.ViewHolders.OrderViewHolder

class OrderAdapter(private val orderList: List<Products>) :
    RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item_layout, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order_item: Products = orderList[position]
        holder.setOrderData(order_item)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}