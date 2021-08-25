package com.example.dunzoclone.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.CartModel
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolder.CartViewHolder

class CartAdapter(private val cartList: List<CartModel>) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_cart_order, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    val cartModel : CartModel = cartList[position]
        holder.setCartData(cartModel)
    }

    override fun getItemCount(): Int {
    return cartList.size
    }

}