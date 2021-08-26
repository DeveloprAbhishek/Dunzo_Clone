package com.example.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dunzoclone.DataModels.CartModel
import com.example.dunzoclone.R
import com.example.dunzoclone.ViewHolders.CartViewHolder

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