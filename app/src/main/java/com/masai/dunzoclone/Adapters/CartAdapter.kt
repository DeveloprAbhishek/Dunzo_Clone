package com.masai.dunzoclone.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.dunzoclone.DataModels.Products
import com.masai.dunzoclone.R
import com.masai.dunzoclone.ViewHolders.CartViewHolder

class CartAdapter(private val cartList: List<Products>) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_cart_order, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
    val cartModel : Products = cartList[position]
        holder.setCartData(cartModel, position)
    }

    override fun getItemCount(): Int {
    return cartList.size
    }

}