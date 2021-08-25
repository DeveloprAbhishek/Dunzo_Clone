package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.DataModels.Order_Model
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.OrderViewHolder

class OrderAdapter(private val orderList: List<Order_Model>) :
    RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item_layout, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order_item: Order_Model = orderList[position]
        holder.setOrderData(order_item)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}